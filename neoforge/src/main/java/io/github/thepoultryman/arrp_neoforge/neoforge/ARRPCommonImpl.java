package io.github.thepoultryman.arrp_neoforge.neoforge;

import io.github.thepoultryman.arrp_neoforge.api.ARRPEvent;
import net.minecraft.server.packs.PackResources;

import java.util.ArrayList;
import java.util.List;

public class ARRPCommonImpl {
    public static void startEventBus() {
        ARRPForNeoForge.ARRP_EVENT_BUS.start();
    }

    public static List<PackResources> sendEvent(ARRPEvent event) {
        ARRPNeoForgeEvent neoForgeEvent = null;
        switch (event) {
            case BeforeUser -> neoForgeEvent = new ARRPNeoForgeEvent.BeforeUserNeoForgeEvent();
            case AfterVanilla -> neoForgeEvent = new ARRPNeoForgeEvent.AfterVanillaNeoForgeEvent();
        }
        if (neoForgeEvent != null) {
            ARRPForNeoForge.ARRP_EVENT_BUS.post(neoForgeEvent);
            return  neoForgeEvent.getPacks();
        } else {
            return new ArrayList<>(0);
        }
    }
}
