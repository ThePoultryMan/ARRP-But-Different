package io.github.thepoultryman.arrp_but_different.json.recipe.component.variant;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.SerializedName;
import io.github.thepoultryman.arrp_but_different.json.recipe.component.AbstractJComponent;

import java.lang.reflect.Type;

public class JFoxVariantComponent extends AbstractJComponent {
    private final Variant variant;

    public JFoxVariantComponent(Variant variant) {
        this.variant = variant;
    }

    public enum Variant {
        @SerializedName("red")
        Red,
        @SerializedName("snow")
        Snow
    }

    public static class Serializer implements JsonSerializer<JFoxVariantComponent> {
        @Override
        public JsonElement serialize(JFoxVariantComponent src, Type type, JsonSerializationContext context) {
            return context.serialize(src.variant);
        }
    }
}
