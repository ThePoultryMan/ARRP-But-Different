package io.github.thepoultryman.arrp_neoforge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = ARRPForNeoForge.MODID, dist = Dist.CLIENT)
public class ARRPForNeoForgeClient {
    public ARRPForNeoForgeClient(ModContainer modContainer) {
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}
