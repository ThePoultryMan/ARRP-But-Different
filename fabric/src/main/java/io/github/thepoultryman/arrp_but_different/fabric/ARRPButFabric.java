package io.github.thepoultryman.arrp_but_different.fabric;

import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import io.github.thepoultryman.arrp_but_different.ARRPCommon;
import io.github.thepoultryman.arrp_but_different.ARRPConfigSpec;
import net.fabricmc.api.ModInitializer;
import net.neoforged.fml.config.ModConfig;

public class ARRPButFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ConfigRegistry.INSTANCE.register(ARRPCommon.MOD_ID, ModConfig.Type.COMMON, ARRPConfigSpec.CONFIG);
    }
}
