package io.github.thepoultryman.arrp_but_different.json.loot;

import com.google.gson.*;
import io.github.thepoultryman.arrp_but_different.json.JCondition;
import io.github.thepoultryman.arrp_but_different.util.BaseCloneable;
import net.minecraft.resources.ResourceLocation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JFunction extends BaseCloneable<JFunction> {
    private final List<JCondition> conditions = new ArrayList<>();
    private JsonObject properties = new JsonObject();

    public JFunction(String function) {
        this.properties.addProperty("function", function);
    }

    public JFunction set(JsonObject properties) {
        properties.addProperty("function", this.properties.get("function").getAsString());
        this.properties = properties;
        return this;
    }

    public JFunction parameter(String key, JsonElement value) {
        this.properties.add(key, value);
        return this;
    }

    public JFunction parameter(String key, Number value) {
        return this.parameter(key, new JsonPrimitive(value));
    }

    public JFunction parameter(String key, boolean value) {
        return this.parameter(key, new JsonPrimitive(value));
    }

    public JFunction parameter(String key, char value) {
        return this.parameter(key, new JsonPrimitive(value));
    }

    public JFunction parameter(String key, ResourceLocation value) {
        return this.parameter(key, value.toString());
    }

    public JFunction parameter(String key, String value) {
        return this.parameter(key, new JsonPrimitive(value));
    }

    public JFunction condition(JCondition condition) {
        this.conditions.add(condition);
        return this;
    }

    public static class Serializer implements JsonSerializer<JFunction> {
        @Override
        public JsonElement serialize(JFunction src, Type typeOfSrc, JsonSerializationContext context) {
            if (!src.conditions.isEmpty()) {
                src.properties.add("conditions", context.serialize(src.conditions));
            }
            return src.properties;
        }
    }
}
