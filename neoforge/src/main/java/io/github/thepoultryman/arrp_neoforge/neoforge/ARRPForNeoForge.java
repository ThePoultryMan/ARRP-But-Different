package io.github.thepoultryman.arrp_neoforge.neoforge;

import com.mojang.logging.LogUtils;
import io.github.thepoultryman.arrp_neoforge.ARRPCommon;
import net.neoforged.bus.api.BusBuilder;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(ARRPCommon.MOD_ID)
public class ARRPForNeoForge {

    public static final IEventBus ARRP_EVENT_BUS = BusBuilder.builder().classChecker(eventClass -> {
        if (eventClass.equals(ARRPNeoForgeEvent.class)) {
            throw new IllegalArgumentException("Only ARRP events are allowed on the ARRP_EVENT_BUS.");
        }
    }).startShutdown().build();

    public ARRPForNeoForge(ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, ARRPForNeoForgeConfig.CONFIG);
    }
}
