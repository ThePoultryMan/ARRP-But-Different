package io.github.thepoultryman.arrp_neoforge.json.model;

import com.google.gson.annotations.SerializedName;
import io.github.thepoultryman.arrp_neoforge.util.BaseCloneable;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class JModel extends BaseCloneable<JModel> {
    private String parent;
    @SerializedName("ambientocclusion")
    private Boolean ambientOcclusion;
    private JDisplay display;
    private JTextures textures;
    private List<JElement> elements;
    private List<JOverride> overrides;

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
}
