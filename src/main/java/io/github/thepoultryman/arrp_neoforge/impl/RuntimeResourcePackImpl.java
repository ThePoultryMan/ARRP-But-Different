package io.github.thepoultryman.arrp_neoforge.impl;

import com.google.gson.JsonObject;
import io.github.thepoultryman.arrp_neoforge.ARRPForNeoForge;
import io.github.thepoultryman.arrp_neoforge.api.RuntimeResourcePack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.AbstractPackResources;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.resources.IoSupplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class RuntimeResourcePackImpl implements RuntimeResourcePack {
    private static final int RESOURCE_PACK_VERSION = 34;

    private final Set<String> KEY_WARNINGS = Collections.newSetFromMap(new ConcurrentHashMap<>());

    private final ResourceLocation id;
    private final PackLocationInfo info;
    private final Lock lock = new ReentrantLock();
    private final Map<ResourceLocation, Supplier<byte[]>> assets = new ConcurrentHashMap<>();
    private final Map<ResourceLocation, Supplier<byte[]>> data = new ConcurrentHashMap<>();
    private final Map<List<String>, Supplier<byte[]>> root = new ConcurrentHashMap<>();

    public RuntimeResourcePackImpl(ResourceLocation resourceLocation) {
        this.id = resourceLocation;
        this.info = new PackLocationInfo(
                this.id.getNamespace() + ";" + this.id.getPath(),
                Component.literal("Runtime Resource Pack for " + this.id),
                PackSource.DEFAULT,
                Optional.empty());
    }

    @Override
    public @Nullable IoSupplier<InputStream> getRootResource(String @NotNull ... pElements) {
        this.lock();
        Supplier<byte[]> byteArraySupplier = this.root.get(Arrays.asList(pElements));
        if (byteArraySupplier == null) {
            this.lock.unlock();
            return null;
        }
        this.lock.unlock();
        return () -> new ByteArrayInputStream(byteArraySupplier.get());
    }

    @Override
    public @Nullable IoSupplier<InputStream> getResource(@NotNull PackType pPackType, @NotNull ResourceLocation pLocation) {
        this.lock();
        Supplier<byte[]> byteArraySupplier = this.getSys(pPackType).get(pLocation);
        if (byteArraySupplier == null) {
            this.lock.unlock();
            return null;
        }
        this.lock.unlock();
        return () -> new ByteArrayInputStream(byteArraySupplier.get());
    }

    @Override
    public void listResources(@NotNull PackType pPackType, @NotNull String pNamespace, @NotNull String pPath, @NotNull ResourceOutput pResourceOutput) {
        this.lock();
        for (ResourceLocation resourceLocation : this.getSys(pPackType).keySet()) {
            Supplier<byte[]> byteArraySupplier = this.getSys(pPackType).get(resourceLocation);
            if (byteArraySupplier == null) {
                this.lock.unlock();
                continue;
            }
            IoSupplier<InputStream> ioSupplier = () -> new ByteArrayInputStream(byteArraySupplier.get());
            if (resourceLocation.getNamespace().equals(pNamespace) && resourceLocation.getPath().startsWith(pPath)) {
                pResourceOutput.accept(resourceLocation, ioSupplier);
            }
        }
        this.lock.unlock();
    }

    @Override
    public @NotNull Set<String> getNamespaces(@NotNull PackType pType) {
        this.lock();
        Set<String> namespaces = new HashSet<>();
        for (ResourceLocation resourceLocation : this.getSys(pType).keySet()) {
            namespaces.add(resourceLocation.getNamespace());
        }
        this.lock.unlock();
        return namespaces;
    }

    @Override
    public @Nullable <T> T getMetadataSection(@NotNull MetadataSectionSerializer<T> pDeserializer) {
        InputStream inputStream = null;
        try {
            IoSupplier<InputStream> ioSupplier = this.getRootResource("pack.mcmeta");
            if (ioSupplier != null) {
                inputStream = ioSupplier.get();
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        if (inputStream != null) {
            return AbstractPackResources.getMetadataFromStream(pDeserializer, inputStream);
        } else {
            if (pDeserializer.getMetadataSectionName().equals("pack")) {
                JsonObject object = new JsonObject();
                object.addProperty("pack_format", RESOURCE_PACK_VERSION);
                object.addProperty("description", "runtime resource pack");
                return pDeserializer.fromJson(object);
            }
            if (KEY_WARNINGS.add(pDeserializer.getMetadataSectionName())) {
                ARRPForNeoForge.LOGGER.info("\"{}\" is an unsupported metadata key", pDeserializer.getMetadataSectionName());
            }
            return null;
        }
    }

    @Override
    public @NotNull PackLocationInfo location() {
        return this.info;
    }

    @Override
    public void close() {
        ARRPForNeoForge.LOGGER.info("Closing Runtime Resource Pack {}", this.id);
        this.lock.unlock();
    }

    private Map<ResourceLocation, Supplier<byte[]>> getSys(PackType packType) {
        return packType == PackType.CLIENT_RESOURCES ? this.assets : this.data;
    }

    private void lock() {
        if (!this.lock.tryLock()) {
            this.lock.lock();
        }
    }
}
