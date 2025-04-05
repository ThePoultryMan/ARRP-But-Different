package io.github.thepoultryman.arrp_but_different.json.recipe.component;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class JBlockStateComponent extends AbstractJComponent {
    private final Map<String, String> states = new HashMap<>();

    public JBlockStateComponent state(String state, String value) {
        this.states.put(state, value);
        return this;
    }

    public static class Serializer implements JsonSerializer<JBlockStateComponent> {
        @Override
        public JsonElement serialize(JBlockStateComponent src, Type type, JsonSerializationContext context) {
            return context.serialize(src.states);
        }
    }
}
