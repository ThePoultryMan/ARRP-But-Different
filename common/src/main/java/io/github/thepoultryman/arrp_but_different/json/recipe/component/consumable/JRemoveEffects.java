package io.github.thepoultryman.arrp_but_different.json.recipe.component.consumable;

import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class JRemoveEffects extends JConsumeEffect {
    private final List<ResourceLocation> effects = new ArrayList<>();

    public JRemoveEffects() {
        super("minecraft:remove_effects");
    }

    public JRemoveEffects effect(ResourceLocation effect) {
        this.effects.add(effect);
        return this;
    }
}
