package io.github.thepoultryman.arrp_but_different.neoforge;

import io.github.thepoultryman.arrp_but_different.ARRPCommon;
import io.github.thepoultryman.arrp_but_different.ARRPConfigSpec;
import net.neoforged.bus.api.BusBuilder;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;

@Mod(ARRPCommon.MOD_ID)
public class ARRPForNeoForge {
    public static final IEventBus ARRP_EVENT_BUS = BusBuilder.builder().classChecker(eventClass -> {
        if (eventClass.equals(ARRPNeoForgeEvent.class)) {
            throw new IllegalArgumentException("Only ARRP events are allowed on the ARRP_EVENT_BUS.");
        }
    }).startShutdown().build();

    public ARRPForNeoForge(ModContainer modContainer, IEventBus modBus) {
        modContainer.registerConfig(ModConfig.Type.COMMON, ARRPConfigSpec.CONFIG);
        modBus.addListener((ModConfigEvent event) -> {
            ARRPConfigSpec.load();
        });
    }
}
