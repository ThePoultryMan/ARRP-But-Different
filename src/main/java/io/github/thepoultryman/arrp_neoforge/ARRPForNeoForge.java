package io.github.thepoultryman.arrp_neoforge;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(ARRPForNeoForge.MODID)
public class ARRPForNeoForge {
    public static final String MODID = "advanced_runtime_resource_pack_for_neoforge";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ARRPForNeoForge(IEventBus modEventBus, ModContainer modContainer) {
    }
}
