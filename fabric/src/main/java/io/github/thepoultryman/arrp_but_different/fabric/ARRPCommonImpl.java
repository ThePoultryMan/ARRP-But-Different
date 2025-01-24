package io.github.thepoultryman.arrp_but_different.fabric;

import io.github.thepoultryman.arrp_but_different.api.ARRPEventTypes;
import io.github.thepoultryman.arrp_but_different.util.AddOnlyList;
import net.minecraft.server.packs.PackResources;

import java.util.List;

public class ARRPCommonImpl {
    public static List<PackResources> sendEvent(ARRPEventTypes event, List<PackResources> resourcePacks) {
        AddOnlyList<PackResources> packs = new AddOnlyList<>(resourcePacks);
        switch (event) {
            case BeforeVanilla -> ARRPEvent.BEFORE_VANILLA.invoker().insert(packs);
            case BeforeUser -> ARRPEvent.BEFORE_USER.invoker().insert(packs);
            case AfterVanilla -> ARRPEvent.AFTER_VANILLA.invoker().insert(packs);
            case BetweenVanillaAndMods -> ARRPEvent.BETWEEN_VANILLA_AND_MODS.invoker().insert(packs);
            case BetweenModsAndUser -> ARRPEvent.BETWEEN_MODS_AND_USER.invoker().insert(packs);
        }

        return packs;
    }

    public static void startEventBus() {}
}
