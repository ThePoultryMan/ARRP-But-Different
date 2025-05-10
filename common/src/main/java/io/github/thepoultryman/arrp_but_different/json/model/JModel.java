package io.github.thepoultryman.arrp_but_different.json.model;

import com.google.gson.annotations.SerializedName;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class JModel {
    private String parent;
    @SerializedName("ambientocclusion")
    private Boolean ambientOcclusion;
    private JDisplay display;
    private JTextures textures;
    private List<JElement> elements;
    private List<JOverride> overrides;
    @SerializedName("render_type")
    private String renderType;

    public static JModel modelKeepElements() {
        JModel model = new JModel();
        model.elements = null;
        return model;
    }

    public static JModel modelKeepElements(String parent) {
        JModel model = new JModel();
        model.parent = parent;
        model.elements = null;
        return model;
    }

    public static JModel modelKeepElements(ResourceLocation parent) {
        return modelKeepElements(parent.toString());
    }

    public static JModel model(String parent) {
        JModel model = new JModel();
        model.parent = parent;
        return model;
    }

    public JModel addOverride(JOverride override) {
        if (this.overrides == null) {
            this.overrides = new ArrayList<>();
        }
        this.overrides.add(override);
        return this;
    }

    public JModel parent(String parent) {
        this.parent = parent;
        return this;
    }

    public JModel noAmbientOcclusion() {
        this.ambientOcclusion = false;
        return this;
    }

    public JModel display(JDisplay display) {
        this.display = display;
        return this;
    }

    public JModel textures(JTextures textures) {
        this.textures = textures;
        return this;
    }

    public JModel element(JElement... elements) {
        if (this.elements == null) {
            this.elements = new ArrayList<>();
        }
        this.elements.addAll(List.of(elements));
        return this;
    }

    /**
     * Sets the render type used for the model. Only used on NeoForge.
     * @param renderType The render type used by the model.
     * @return The current {@link JModel} instance.
     */
    public JModel renderType(@NotNull RenderType renderType) {
        if (renderType.equals(RenderType.solid())) {
            this.renderType = "minecraft:solid";
        } else if (renderType.equals(RenderType.cutout())) {
            this.renderType = "minecraft:cutout";
        } else if (renderType.equals(RenderType.cutoutMipped())) {
            this.renderType = "minecraft:cutout_mipped";
        } else if (renderType.equals(RenderType.translucent())) {
            this.renderType = "minecraft:translucent";
        } else if (renderType.equals(RenderType.tripwire())) {
            this.renderType = "minecraft:tripwire";
        }
        return this;
    }

    public JModel renderType(String renderType) {
        this.renderType = renderType;
        return this;
    }
}
