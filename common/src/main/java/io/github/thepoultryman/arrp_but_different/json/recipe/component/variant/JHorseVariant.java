package io.github.thepoultryman.arrp_but_different.json.recipe.component.variant;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.SerializedName;
import io.github.thepoultryman.arrp_but_different.json.recipe.component.AbstractJComponent;

import java.lang.reflect.Type;

public class JHorseVariant extends AbstractJComponent {
    private final Variant variant;

    public JHorseVariant(Variant variant) {
        this.variant = variant;
    }

    public enum Variant {
        @SerializedName("white")
        White,
        @SerializedName("creamy")
        Creamy,
        @SerializedName("chestnut")
        Chestnut,
        @SerializedName("brown")
        Brown,
        @SerializedName("black")
        Black,
        @SerializedName("gray")
        Gray,
        @SerializedName("dark_brown")
        DarkBrown
    }

    public static class Serializer implements JsonSerializer<JHorseVariant> {
        @Override
        public JsonElement serialize(JHorseVariant src, Type type, JsonSerializationContext context) {
            return context.serialize(src.variant);
        }
    }
}
