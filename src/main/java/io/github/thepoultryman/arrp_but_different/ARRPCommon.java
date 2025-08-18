package io.github.thepoultryman.arrp_but_different;

import io.github.thepoultryman.arrp_but_different.api.ARRPEventTypes;
import io.github.thepoultryman.arrp_but_different.neoforge.ARRPForNeoForge;
import io.github.thepoultryman.arrp_but_different.neoforge.ARRPNeoForgeEvent;
import net.minecraft.server.packs.PackResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
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
        //? }
    }

    public static void startEventBus() {
        //? if neoforge {
        ARRPCommon.LOGGER.info("Starting event bus");
        ARRPForNeoForge.ARRP_EVENT_BUS.start();
        //? }
    }
}
