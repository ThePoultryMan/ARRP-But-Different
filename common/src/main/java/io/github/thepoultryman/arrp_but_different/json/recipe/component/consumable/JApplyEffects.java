package io.github.thepoultryman.arrp_but_different.json.recipe.component.consumable;

import com.google.gson.annotations.SerializedName;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class JApplyEffects extends JConsumeEffect {
    private final List<JEffect> effects = new ArrayList<>();

    public JApplyEffects() {
        super("minecraft:apply_effects");
    }

    public JApplyEffects effect(JEffect effect) {
        this.effects.add(effect);
        return this;
    }

    public static class JEffect {
        private final ResourceLocation id;
        private int amplifier;
        private int duration;
        private boolean ambient;
        @SerializedName("show_particles")
        private boolean showParticles;
        @SerializedName("show_icon")
        private boolean showIcon;
        @SerializedName("hidden_effect")
        private JEffect hiddenEffect;

        public JEffect(ResourceLocation id) {
            this.id = id;
        }

        public JEffect amplifier(int amplifier) {
            this.amplifier = amplifier;
            return this;
        }

        public JEffect duration(int duration) {
            this.duration = duration;
            return this;
        }

        public JEffect ambient(boolean ambient) {
            this.ambient = ambient;
            return this;
        }

        public JEffect showParticles(boolean showParticles) {
            this.showParticles = showParticles;
            return this;
        }

        public JEffect showIcon(boolean showIcon) {
            this.showIcon = showIcon;
            return this;
        }

        public JEffect hiddenEffect(JEffect hiddenEffect) {
            this.hiddenEffect = hiddenEffect;
            return this;
        }
    }
}
