package io.github.thepoultryman.arrp_but_different.neoforge;

import io.github.thepoultryman.arrp_but_different.ARRPCommon;
import io.github.thepoultryman.arrp_but_different.api.ARRPEventTypes;
import net.minecraft.server.packs.PackResources;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ARRPCommonImpl {
    public static void startEventBus() {
        ARRPCommon.LOGGER.info("Starting event bus");
        ARRPForNeoForge.ARRP_EVENT_BUS.start();
    }

    public static List<PackResources> sendEvent(ARRPEventTypes event, List<PackResources> resourcePacks) {
        ARRPNeoForgeEvent neoForgeEvent = null;
        if (Objects.requireNonNull(event) == ARRPEventTypes.BeforeUser) {
            neoForgeEvent = new ARRPNeoForgeEvent.BeforeUser();
        } else {
            ARRPCommon.LOGGER.error("{} is not currently supported on NeoForge", event);
        }
        ARRPForNeoForge.ARRP_EVENT_BUS.post(neoForgeEvent);
        return neoForgeEvent != null ? neoForgeEvent.getPacks() : new ArrayList<>();
    }
}
