package io.github.thepoultryman.arrp_but_testing.neoforge;

import io.github.thepoultryman.arrp_but_different.neoforge.ARRPForNeoForge;
import io.github.thepoultryman.arrp_but_different.neoforge.ARRPNeoForgeEvent;
import io.github.thepoultryman.arrp_but_testing.ARRPButTestingCommon;
import net.neoforged.fml.common.Mod;

@Mod(ARRPButTestingCommon.MOD_ID)
public class ARRPNeoForgeTest {
    public ARRPNeoForgeTest() {
        ARRPForNeoForge.ARRP_EVENT_BUS.addListener((ARRPNeoForgeEvent.BeforeUser event) -> event.addPack(ARRPButTestingCommon.beforeUserEvent()));
    }
}