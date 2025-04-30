package io.github.thepoultryman.arrp_but_different.json.recipe.component;

import com.google.gson.annotations.SerializedName;
import io.github.thepoultryman.arrp_but_different.json.recipe.JAttributeModifier;

import java.util.ArrayList;
import java.util.List;

public class JAttributeModifiersComponent extends AbstractJComponent {
    private List<JAttributeModifier> modifiers = new ArrayList<>();
    @SerializedName("show_in_tooltip")
    private Boolean showInTooltip;

    public JAttributeModifiersComponent() {}

    public JAttributeModifiersComponent(boolean showInTooltip) {
        this.showInTooltip = showInTooltip;
    }

    public JAttributeModifiersComponent showInTooltip(boolean showInTooltip) {
        this.showInTooltip = showInTooltip;
        return this;
    }

    public JAttributeModifiersComponent modifier(JAttributeModifier modifier) {
        this.modifiers.add(modifier);
        return this;
    }
}
