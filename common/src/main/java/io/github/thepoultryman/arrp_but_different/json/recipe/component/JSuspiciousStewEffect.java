package io.github.thepoultryman.arrp_but_different.json.recipe.component;

import net.minecraft.resources.ResourceLocation;

public class JSuspiciousStewEffect {
    private final ResourceLocation id;
    private Integer duration;

    public JSuspiciousStewEffect(ResourceLocation id) {
        this.id = id;
    }

    public JSuspiciousStewEffect duration(int duration) {
        this.duration = duration;
        return this;
    }
}
