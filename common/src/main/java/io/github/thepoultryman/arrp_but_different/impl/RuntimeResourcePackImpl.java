package io.github.thepoultryman.arrp_but_different.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import io.github.thepoultryman.arrp_but_different.ARRPCommon;
import io.github.thepoultryman.arrp_but_different.ARRPConfig;
import io.github.thepoultryman.arrp_but_different.api.RuntimeResourcePack;
import io.github.thepoultryman.arrp_but_different.json.JCondition;
import io.github.thepoultryman.arrp_but_different.json.JLang;
import io.github.thepoultryman.arrp_but_different.json.JTag;
import io.github.thepoultryman.arrp_but_different.json.animation.JAnimation;
import io.github.thepoultryman.arrp_but_different.json.loot.JFunction;
import io.github.thepoultryman.arrp_but_different.json.loot.JLootTable;
import io.github.thepoultryman.arrp_but_different.json.loot.JPool;
import io.github.thepoultryman.arrp_but_different.json.model.JModel;
import io.github.thepoultryman.arrp_but_different.json.model.JTextures;
import io.github.thepoultryman.arrp_but_different.json.recipe.AbstractJRecipe;
import io.github.thepoultryman.arrp_but_different.json.recipe.JIngredient;
import io.github.thepoultryman.arrp_but_different.json.recipe.banner.JBannerPatternType;
import io.github.thepoultryman.arrp_but_different.json.recipe.component.*;
import io.github.thepoultryman.arrp_but_different.json.recipe.component.consumable.JSound;
import io.github.thepoultryman.arrp_but_different.json.state.JMultipart;
import io.github.thepoultryman.arrp_but_different.json.state.JState;
import io.github.thepoultryman.arrp_but_different.json.state.JWhen;
import io.github.thepoultryman.arrp_but_different.util.CountingInputStream;
import io.github.thepoultryman.arrp_but_different.util.ResourceLocationSerializer;
import io.github.thepoultryman.arrp_but_different.util.UnsafeByteArrayOutputStream;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.AbstractPackResources;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionType;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.resources.IoSupplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class RuntimeResourcePackImpl implements RuntimeResourcePack {
    private static final int RESOURCE_PACK_VERSION = 55;

    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(JMultipart.class, new JMultipart.Serializer())
            .registerTypeAdapter(JWhen.class, new JWhen.Serializer())
            .registerTypeAdapter(JState.class, new JState.Serializer())
            .registerTypeAdapter(JTextures.class, new JTextures.Serializer())
            .registerTypeAdapter(JAnimation.class, new JAnimation.Serializer())
            .registerTypeAdapter(JFunction.class, new JFunction.Serializer())
            .registerTypeAdapter(JPool.class, new JPool.Serializer())
            .registerTypeAdapter(JIngredient.class, new JIngredient.Serializer())
            .registerTypeAdapter(ResourceLocation.class, new ResourceLocationSerializer())
            .registerTypeAdapter(JCondition.class, new JCondition.Serializer())
            .registerTypeAdapter(JCodecComponent.class, new JCodecComponent.Serializer<>())
            .registerTypeAdapter(JSimpleComponent.class, new JSimpleComponent.Serializer())
            .registerTypeAdapter(JBannerPatternsComponent.class, new JBannerPatternsComponent.Serializer())
            .registerTypeAdapter(JBannerPatternType.class, new JBannerPatternType.Serializer())
            .registerTypeAdapter(JColorComponent.class, new JColorComponent.Serializer())
            .registerTypeAdapter(JBeesComponent.class, new JBeesComponent.Serializer())
            .registerTypeAdapter(JBeesComponent.BeeData.class, new JBeesComponent.BeeData.Serializer())
            .registerTypeAdapter(JStringMapComponent.class, new JStringMapComponent.Serializer())
            .registerTypeAdapter(JItemListComponent.class, new JItemListComponent.Serializer())
            .registerTypeAdapter(JSound.class, new JSound.Serializer())
            .registerTypeAdapter(JContainerComponent.class, new JContainerComponent.Serializer())
            .registerTypeAdapter(JMultitypeComponent.class, new JMultitypeComponent.Serializer())
            .registerTypeAdapter(JCustomDataComponent.class, new JCustomDataComponent.Serializer())
            .registerTypeAdapter(JDyedColorComponent.class, new JDyedColorComponent.Serializer())
            .registerTypeAdapter(JEnchantmentsComponent.class, new JEnchantmentsComponent.Serializer())
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();
    private final Set<String> KEY_WARNINGS = Collections.newSetFromMap(new ConcurrentHashMap<>());

    private final ResourceLocation id;
    private final PackLocationInfo info;
    private final Lock lock = new ReentrantLock();
    private final Map<ResourceLocation, Supplier<byte[]>> assets = new ConcurrentHashMap<>();
    private final Map<ResourceLocation, Supplier<byte[]>> data = new ConcurrentHashMap<>();
    private final Map<List<String>, Supplier<byte[]>> root = new ConcurrentHashMap<>();
    private final Map<ResourceLocation, JLang> langMergeable = new ConcurrentHashMap<>();

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
    public @Nullable <T> T getMetadataSection(@NotNull MetadataSectionType<T> pSectionType) {
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
            return AbstractPackResources.getMetadataFromStream(pSectionType, inputStream);
        } else {
            if (pSectionType.name().equals("pack")) {
                JsonObject object = new JsonObject();
                object.addProperty("pack_format", RESOURCE_PACK_VERSION);
                object.addProperty("description", "runtime resource pack");
                DataResult<T> result = pSectionType.codec().parse(JsonOps.INSTANCE, object);
                if (result.isSuccess()) {
                    return result.getOrThrow();
                } else {
                    throw new RuntimeException("Resource Pack information could not be parsed.");
                }
            }
            if (KEY_WARNINGS.add(pSectionType.name())) {
                ARRPCommon.LOGGER.info("\"{}\" is an unsupported metadata key", pSectionType.name());
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
        ARRPCommon.LOGGER.info("Closing Runtime Resource Pack {}", this.id);

        if (ARRPConfig.dump) {
            this.lock();
            this.dump();
            this.lock.unlock();
        }
    }

    private void write(Path dir, ResourceLocation identifier, byte[] data) {
        try {
            String namespace = identifier.getNamespace();
            String path = identifier.getPath();
            Path file = dir.resolve(namespace).resolve(path);
            if(file.toAbsolutePath().startsWith(dir.toAbsolutePath())) {
                Files.createDirectories(file.getParent());
                try(OutputStream output = Files.newOutputStream(file)) {
                    output.write(data);
                }
            } else {
                ARRPCommon.LOGGER.error("RRP contains out-of-directory location! \"{}/{}\"", namespace, path);
            }

        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void dump() {
        ARRPCommon.LOGGER.info("Dumping generated resources for \"{}\"", this.id);

        Path output = Path.of(ARRPConfig.dumpDirectory);
        try {
            for (Map.Entry<List<String>, Supplier<byte[]>> e : this.root.entrySet()) {
                String pathStr = String.join("/", e.getKey());
                Path path = output.resolve(pathStr);
                if (path.toAbsolutePath().startsWith(output.toAbsolutePath())) {
                    Files.createDirectories(path.getParent());
                    Files.write(path, e.getValue().get());
                } else {
                    ARRPCommon.LOGGER.error("RRP contains out-of-directory path! \"{}\"", pathStr);
                }
            }

            Path assets = output.resolve("assets");
            Files.createDirectories(assets);
            for (Map.Entry<ResourceLocation, Supplier<byte[]>> entry : this.assets.entrySet()) {
                this.write(assets, entry.getKey(), entry.getValue().get());
            }

            Path data = output.resolve("data");
            Files.createDirectories(data);
            for (Map.Entry<ResourceLocation, Supplier<byte[]>> entry : this.data.entrySet()) {
                this.write(data, entry.getKey(), entry.getValue().get());
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);

        }
    }

    private Map<ResourceLocation, Supplier<byte[]>> getSys(PackType packType) {
        return packType == PackType.CLIENT_RESOURCES ? this.assets : this.data;
    }

    private void lock() {
        if (!this.lock.tryLock()) {
            this.lock.lock();
        }
    }

    @Override
    public void addRecoloredImage(ResourceLocation resourceLocation, InputStream original, IntUnaryOperator pixel) {
        this.addLazyResource(
                PackType.CLIENT_RESOURCES,
                formatResourceLocation(resourceLocation, "textures", "png"),
                (runtimeResourcePack, resourceLocation1) -> {
                    try {
                        CountingInputStream countingInputStream = new CountingInputStream(original);
                        BufferedImage base = ImageIO.read(original);
                        BufferedImage recolored = new BufferedImage(base.getWidth(), base.getHeight(), BufferedImage.TYPE_INT_ARGB);
                        for (int y = 0; y < base.getHeight(); y++) {
                            for (int x = 0; x < base.getWidth(); x++) {
                                recolored.setRGB(x, y, pixel.applyAsInt(base.getRGB(x, y)));
                            }
                        }

                        UnsafeByteArrayOutputStream arrayOutputStream = new UnsafeByteArrayOutputStream(countingInputStream.bytes());
                        ImageIO.write(recolored, "png", arrayOutputStream);
                        return arrayOutputStream.getBytes();
                    } catch (Throwable e) {
                        ARRPCommon.LOGGER.error("Error adding recolored image to runtime resource pack", e);
                        throw new RuntimeException(e);
                    }
                });
    }

    @Override
    public byte[] addLang(ResourceLocation resourceLocation, JLang lang) {
        return this.addAsset(formatResourceLocation(resourceLocation, "lang", "json"), serialize(lang.getLang()));
    }

    @Override
    public void mergeLang(ResourceLocation resourceLocation, JLang lang) {
        this.langMergeable.compute(resourceLocation, (location, language) -> {
            if (language == null) {
                language = new JLang();
                JLang result = language;
                this.addLazyResource(
                        PackType.CLIENT_RESOURCES,
                        resourceLocation,
                        (resourcePack, packLocation) -> resourcePack.addLang(resourceLocation, result));
            }
            language.getLang().putAll(lang.getLang());
            return language;
        });
    }

    @Override
    public byte[] addLootTable(ResourceLocation resourceLocation, JLootTable lootTable) {
        return this.addData(formatResourceLocation(resourceLocation, "loot_tables", "json"), serialize(lootTable));
    }

    @Override
    public void addLazyResource(PackType packType, ResourceLocation path, BiFunction<RuntimeResourcePack, ResourceLocation, byte[]> data) {
        this.getSys(packType).put(path, new Memoized<>(data, path));
    }

    @Override
    public byte[] addResource(PackType packType, ResourceLocation path, byte[] data) {
        this.getSys(packType).put(path, () -> data);
        return data;
    }

    @Override
    public byte[] addRootResource(String path, byte[] data) {
        this.root.put(List.of(path.split("/")), () -> data);
        return data;
    }

    @Override
    public byte[] addAsset(ResourceLocation path, byte[] data) {
        return this.addResource(PackType.CLIENT_RESOURCES, path, data);
    }

    @Override
    public byte[] addData(ResourceLocation path, byte[] data) {
        return this.addResource(PackType.SERVER_DATA, path, data);
    }

    @Override
    public byte[] addModel(ResourceLocation path, JModel model) {
        return this.addAsset(formatResourceLocation(path, "models", "json"), serialize(model));
    }

    @Override
    public byte[] addBlockSate(ResourceLocation path, JState state) {
        return this.addAsset(formatResourceLocation(path, "blockstates", "json"), serialize(state));
    }

    @Override
    public byte[] addTexture(ResourceLocation path, BufferedImage image) {
        UnsafeByteArrayOutputStream byteArrayOutputStream = new UnsafeByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", byteArrayOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this.addAsset(formatResourceLocation(path, "textures", "png"), byteArrayOutputStream.getBytes());
    }

    @Override
    public byte[] addAnimation(ResourceLocation path, JAnimation animation) {
        return this.addAsset(formatResourceLocation(path, "textures", "png.mcmeta"), serialize(animation));
    }

    @Override
    public byte[] addTag(ResourceLocation path, JTag tag) {
        return this.addData(formatResourceLocation(path, "tag", "json"), serialize(tag));
    }

    @Override
    public byte[] addRecipe(ResourceLocation path, AbstractJRecipe recipe) {
        return this.addData(formatResourceLocation(path, "recipes", "json"), serialize(recipe));
    }

    @Override
    public void load(Path directory) throws IOException {
        try (Stream<Path> stream = Files.walk(directory)) {
            for (Path file : (Iterable<Path>) () -> stream.filter(Files::isRegularFile).map(directory::relativize).iterator()) {
                String string = file.toString();
                if (string.startsWith("assets")) {
                    String path = string.substring("assets".length() + 1);
                    this.load(path, this.assets, Files.readAllBytes(file));
                } else if (string.startsWith("data")) {
                    String path = string.substring("data".length() + 1);
                    this.load(path, this.data, Files.readAllBytes(file));
                } else {
                    byte[] data = Files.readAllBytes(file);
                    this.root.put(List.of(string.split("/")), () -> data);
                }
            }
        }
    }

    @Override
    public void load(ZipInputStream zipStream) throws IOException {
        ZipEntry entry;
        while ((entry = zipStream.getNextEntry()) != null) {
            String string = entry.toString();
            if(string.startsWith("assets")) {
                String path = string.substring("assets".length() + 1);
                this.load(path, this.assets, this.read(entry, zipStream));
            } else if(string.startsWith("data")) {
                String path = string.substring("data".length() + 1);
                this.load(path, this.data, this.read(entry, zipStream));
            } else {
                byte[] data = this.read(entry, zipStream);
                this.root.put(Arrays.asList(string.split("/")), () -> data);
            }
        }
    }

    private void load(String fullPath, Map<ResourceLocation, Supplier<byte[]>> map, byte[] data) {
        int separatorIndex = fullPath.indexOf("/");
        String namespace = fullPath.substring(0, separatorIndex);
        String path = fullPath.substring(separatorIndex + 1);
        map.put(ResourceLocation.fromNamespaceAndPath(namespace, path), () -> data);
    }

    private byte[] read(ZipEntry entry, InputStream inputStream) throws IOException {
        byte[] data = new byte[Math.toIntExact(entry.getSize())];
        if (inputStream.read(data) != data.length) {
            throw new IOException("Zip stream was cut off.");
        }
        return data;
    }

    private static byte[] serialize(Object object) {
        UnsafeByteArrayOutputStream byteArrayOutputStream = new UnsafeByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.UTF_8);
        GSON.toJson(object, writer);
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return byteArrayOutputStream.getBytes();
    }

    private static ResourceLocation formatResourceLocation(ResourceLocation resourceLocation, String prefix, String append) {
        return ResourceLocation.fromNamespaceAndPath(resourceLocation.getNamespace(), prefix + "/" + resourceLocation.getPath() + "." + append);
    }

    private class Memoized<T> implements Supplier<byte[]> {
        private final BiFunction<RuntimeResourcePack, T, byte[]> function;
        private final T path;
        private byte[] data;

        public Memoized(BiFunction<RuntimeResourcePack, T, byte[]> function, T path) {
            this.function = function;
            this.path = path;
        }

        @Override
        public byte[] get() {
            if (this.data == null) {
                this.data = this.function.apply(RuntimeResourcePackImpl.this, path);
            }
            return this.data;
        }
    }
}
