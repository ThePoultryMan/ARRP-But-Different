package io.github.thepoultryman.arrp_but_different.mixins;

import io.github.thepoultryman.arrp_but_different.ARRPCommon;
import io.github.thepoultryman.arrp_but_different.api.ARRPEventTypes;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.resources.MultiPackResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@Mixin(MultiPackResourceManager.class)
public class MultiPackResourceManagerMixin {
    @ModifyVariable(method = "<init>", at = @At("HEAD"), argsOnly = true)
    private static List<PackResources> arrp_but_different$Init(List<PackResources> packResources) {
        List<PackResources> packsCopy = new ArrayList<>(packResources);
        ARRPCommon.LOGGER.info("Getting 'BeforeVanilla' packs");
        packsCopy.addAll(0, ARRPCommon.sendEvent(ARRPEventTypes.BeforeVanilla, new ArrayList<>()));

        List<PackResources> finalPacksCopy = packsCopy;
        OptionalInt fabricPackIndex = IntStream.range(0, packsCopy.size()).filter((i) -> finalPacksCopy.get(i).packId().equals("fabric")).findFirst();

        if (fabricPackIndex.isPresent()) {
            ARRPCommon.LOGGER.info("Getting 'BetweenVanillaAndMods' packs");
            packsCopy.addAll(fabricPackIndex.getAsInt(), ARRPCommon.sendEvent(ARRPEventTypes.BetweenVanillaAndMods, new ArrayList<>()));
            ARRPCommon.LOGGER.info("Getting 'BetweenModsAndUser' packs");
            packsCopy.addAll(fabricPackIndex.getAsInt() + 1, ARRPCommon.sendEvent(ARRPEventTypes.BetweenModsAndUser, new ArrayList<>()));
        }

        ARRPCommon.LOGGER.info("Getting 'AfterVanilla' packs");
        packsCopy = ARRPCommon.sendEvent(ARRPEventTypes.AfterVanilla, packsCopy);

        return packsCopy;
    }
}
