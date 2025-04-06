package io.github.thepoultryman.arrp_but_different.json.recipe.component;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class JPrimitiveComponent<T> extends AbstractJComponent {
    private final T value;

    public JPrimitiveComponent(T value) {
        this.value = value;
    }

    public static class Serializer implements JsonSerializer<JPrimitiveComponent<?>> {
        @Override
        public JsonElement serialize(JPrimitiveComponent<?> src, Type type, JsonSerializationContext context) {
            return context.serialize(src.value);
        }
    }
}
