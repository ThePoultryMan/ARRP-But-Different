package io.github.thepoultryman.arrp_but_different;

import io.github.thepoultryman.arrp_but_different.api.ARRPEventTypes;
//? if neoforge {
import io.github.thepoultryman.arrp_but_different.neoforge.ARRPForNeoForge;
import io.github.thepoultryman.arrp_but_different.neoforge.ARRPNeoForgeEvent;

//?} else {
/*import io.github.thepoultryman.arrp_but_different.fabric.ARRPEvent;
import io.github.thepoultryman.arrp_but_different.util.AddOnlyList;
*///?}
import net.minecraft.server.packs.PackResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
//? if neoforge
 import java.util.Objects; 

public class ARRPCommon {
    public static final String MOD_ID = "advanced_runtime_resource_packs_but_different";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static List<PackResources> sendEvent(ARRPEventTypes event, List<PackResources> resourcePacks) {
        //? if neoforge {
        ARRPNeoForgeEvent neoForgeEvent = null;
        if (Objects.requireNonNull(event) == ARRPEventTypes.BeforeUser) {
            neoForgeEvent = new ARRPNeoForgeEvent.BeforeUser();
            ARRPForNeoForge.ARRP_EVENT_BUS.post(neoForgeEvent);
        } else {
            ARRPCommon.LOGGER.error("{} is not currently supported on NeoForge", event);
        }
        return neoForgeEvent != null ? neoForgeEvent.getPacks() : resourcePacks;
        //?} else {
        /*AddOnlyList<PackResources> packs = new AddOnlyList<>(resourcePacks);
        switch (event) {
            case BeforeVanilla -> ARRPEvent.BEFORE_VANILLA.invoker().insert(packs);
            case BeforeUser -> ARRPEvent.BEFORE_USER.invoker().insert(packs);
            case AfterVanilla -> ARRPEvent.AFTER_VANILLA.invoker().insert(packs);
            case BetweenVanillaAndMods -> ARRPEvent.BETWEEN_VANILLA_AND_MODS.invoker().insert(packs);
            case BetweenModsAndUser -> ARRPEvent.BETWEEN_MODS_AND_USER.invoker().insert(packs);
        }

        return packs;
        *///?}
    }

    public static void startEventBus() {
        //? if neoforge {
        ARRPCommon.LOGGER.info("Starting event bus");
        ARRPForNeoForge.ARRP_EVENT_BUS.start();
        //?}
    }
}
