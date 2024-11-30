package io.github.thepoultryman.arrp_neoforge.json.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JIngredients {
    private final List<JIngredient> ingredients = new ArrayList<>();

    public JIngredients add(JIngredient ingredient) {
        this.ingredients.add(ingredient);
        return this;
    }

    public static class Serializer implements JsonSerializer<JIngredient> {
        @Override
        public JsonElement serialize(JIngredient src, Type typeOfSrc, JsonSerializationContext context) {
            return context.serialize(src.ingredients);
        }
    }
}
