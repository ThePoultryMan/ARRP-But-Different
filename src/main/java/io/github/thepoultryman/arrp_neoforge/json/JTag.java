package io.github.thepoultryman.arrp_neoforge.json;

import io.github.thepoultryman.arrp_neoforge.util.BaseCloneable;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class JTag extends BaseCloneable<JTag> {
    private Boolean replace = false;
    private final List<String> values = new ArrayList<>();

    public JTag replace() {
        this.replace = true;
        return this;
    }

    public JTag add(ResourceLocation item) {
        this.values.add(item.toString());
        return this;
    }

    public JTag addTag(ResourceLocation tag) {
        this.values.add("#" + tag.getNamespace() + ":" + tag.getPath());
        return this;
    }
}
