package io.github.thepoultryman.arrp_but_different;

import dev.architectury.injectables.annotations.ExpectPlatform;
import io.github.thepoultryman.arrp_but_different.api.ARRPEventTypes;
import net.minecraft.server.packs.PackResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ARRPCommon {
    public static final String MOD_ID = "advanced_runtime_resource_packs_but_different";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @ExpectPlatform
    public static List<PackResources> sendEvent(ARRPEventTypes event, List<PackResources> resourcePacks) {
        throw new AssertionError("Expected platform integration");
    }

    @ExpectPlatform
    public static void startEventBus() {}
}
