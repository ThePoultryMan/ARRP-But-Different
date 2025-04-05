package io.github.thepoultryman.arrp_but_different.json.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.github.thepoultryman.arrp_but_different.json.recipe.component.AbstractJComponent;
import io.github.thepoultryman.arrp_but_different.json.recipe.component.EmptyJComponent;
import io.github.thepoultryman.arrp_but_different.json.recipe.component.JIntegerComponent;
import io.github.thepoultryman.arrp_but_different.util.BaseCloneable;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class JResult extends BaseCloneable<JResult> {
    private ResourceLocation id;
    private int count;
    // TODO: Add builders for all vanilla components
    private final Map<String, AbstractJComponent> components = new HashMap<>();

    public JResult id(ResourceLocation location) {
        this.id = location;
        return this;
    }

    public JResult count(int count) {
        this.count = count;
        return this;
    }

    // Allow inputting specific components.
    public JResult component(String name, AbstractJComponent value) {
        this.components.put(name, value);
        return this;
    }

    public JResult component(String name, int value) {
        this.components.put(name, new JIntegerComponent(value));
        return this;
    }

    // Helpers for adding components.
    public JResult addAttributeModifiers() {}

    public JResult removedComponent(String name) {
        this.components.put("!" + name, new EmptyJComponent());
        return this;
    }
}
