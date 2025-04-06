package io.github.thepoultryman.arrp_but_different.json.recipe.component.variant;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.SerializedName;
import io.github.thepoultryman.arrp_but_different.json.recipe.component.AbstractJComponent;

import java.lang.reflect.Type;

public class JFrogVariantComponent extends AbstractJComponent {
    private final Variant variant;

    public JFrogVariantComponent(Variant variant) {
        this.variant = variant;
    }

    public enum Variant {
        @SerializedName("minecraft:cold")
        Cold,
        @SerializedName("minecraft:temperate")
        Temperate,
        @SerializedName("minecraft:warm")
        Warm
    }

    public static class Serializer implements JsonSerializer<JFrogVariantComponent> {
        @Override
        public JsonElement serialize(JFrogVariantComponent src, Type type, JsonSerializationContext context) {
            return context.serialize(src.variant);
        }
    }
}
