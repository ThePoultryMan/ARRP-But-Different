package io.github.thepoultryman.arrp_but_different.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.thepoultryman.arrp_but_different.impl.RuntimeResourcePackImpl;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionType;
import net.minecraft.server.packs.metadata.pack.PackFormat;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Pack.class)
public class PackMixin {
    // Use a specialized method for reading the rrp metadata in order always ensure the correct
    // format version is used.
    @WrapOperation(
            method = "readPackMetadata",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/packs/PackResources;getMetadataSection(Lnet/minecraft/server/packs/metadata/MetadataSectionType;)Ljava/lang/Object;")
    )
    private static <T> T arrp_but_different$readPackMetadata(PackResources packResources,
                                                             MetadataSectionType<T> metadataSectionType,
                                                             Operation<PackMetadataSection> original,
                                                             PackLocationInfo packLocationInfo,
                                                             Pack.ResourcesSupplier resourcesSupplier,
                                                             PackFormat packFormat,
                                                             PackType packType)
    {
        if (packResources instanceof RuntimeResourcePackImpl runtimeResourcePack &&
                metadataSectionType.name().equals("pack")
        ) {
            return runtimeResourcePack.getMetadataSection(metadataSectionType, packFormat);
        } else {
            return (T) original.call(packResources, metadataSectionType);
        }
    }
}
