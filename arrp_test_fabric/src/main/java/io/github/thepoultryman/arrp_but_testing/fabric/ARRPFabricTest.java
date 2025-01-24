package io.github.thepoultryman.arrp_but_testing.fabric;

import io.github.thepoultryman.arrp_but_different.fabric.ARRPEvent;
import io.github.thepoultryman.arrp_but_testing.ARRPButTestingCommon;
import net.fabricmc.api.ModInitializer;

public class ARRPFabricTest implements ModInitializer {
    @Override
    public void onInitialize() {;
        ARRPEvent.BEFORE_USER.register((resourcePacks) -> resourcePacks.add(ARRPButTestingCommon.beforeUserEvent()));
    }
}
