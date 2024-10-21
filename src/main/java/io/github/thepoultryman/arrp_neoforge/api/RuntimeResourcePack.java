package io.github.thepoultryman.arrp_neoforge.api;

import io.github.thepoultryman.arrp_neoforge.impl.RuntimeResourcePackImpl;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;

public interface RuntimeResourcePack extends PackResources {
    static RuntimeResourcePack create(ResourceLocation resourceLocation) {
        return new RuntimeResourcePackImpl(resourceLocation);
    }
}
