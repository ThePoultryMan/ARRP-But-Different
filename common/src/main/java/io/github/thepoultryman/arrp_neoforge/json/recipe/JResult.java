package io.github.thepoultryman.arrp_neoforge.json.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.github.thepoultryman.arrp_neoforge.util.BaseCloneable;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class JResult extends BaseCloneable<JResult> {
    private ResourceLocation id;
    private int count;
    // TODO: Add builders for all vanilla components
    private final Map<String, JsonElement> components = new HashMap<>();

    public JResult id(ResourceLocation location) {
        this.id = location;
        return this;
    }

    public JResult count(int count) {
        this.count = count;
        return this;
    }

    public JResult component(String name, JsonObject value) {
        this.components.put(name, value);
        return this;
    }

    public JResult component(String name, int value) {
        this.components.put(name, new JsonPrimitive(value));
        return this;
    }

    public JResult component(String name, String value) {
        this.components.put(name, new JsonPrimitive(value));
        return this;
    }

    public JResult removedComponent(String name) {
        this.components.put("!" + name, new JsonObject());
        return this;
    }
}
