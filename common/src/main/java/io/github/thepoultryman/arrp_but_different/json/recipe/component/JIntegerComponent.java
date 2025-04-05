package io.github.thepoultryman.arrp_but_different.json.recipe.component;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class JIntegerComponent extends AbstractJComponent {
    private final int value;

    public JIntegerComponent(int value) {
        this.value = value;
    }

    public static class Serializer implements JsonSerializer<JIntegerComponent> {
        @Override
        public JsonElement serialize(JIntegerComponent jIntegerComponent, Type type, JsonSerializationContext jsonSerializationContext) {
            return jsonSerializationContext.serialize(jIntegerComponent.value);
        }
    }
}
