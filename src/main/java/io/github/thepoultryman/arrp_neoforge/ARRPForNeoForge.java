package io.github.thepoultryman.arrp_neoforge;

import com.mojang.logging.LogUtils;
import io.github.thepoultryman.arrp_neoforge.api.event.ARRPNeoForgeEvent;
import net.neoforged.bus.api.BusBuilder;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(ARRPForNeoForge.MODID)
public class ARRPForNeoForge {
    public static final String MODID = "advanced_runtime_resource_pack_for_neoforge";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final IEventBus ARRP_EVENT_BUS = BusBuilder.builder().classChecker(eventClass -> {
        if (!ARRPNeoForgeEvent.class.isAssignableFrom(eventClass)) {
            throw new IllegalArgumentException("Only ARRP events are allowed on the ARRP_EVENT_BUS.");
        }
    }).startShutdown().build();

    public ARRPForNeoForge(IEventBus modEventBus, ModContainer modContainer) {}
}
