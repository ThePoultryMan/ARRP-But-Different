package io.github.thepoultryman.arrp_neoforge.json.recipe.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.github.thepoultryman.arrp_neoforge.json.recipe.JIngredient;
import io.github.thepoultryman.arrp_neoforge.util.BaseCloneable;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JKeys extends BaseCloneable<JKeys> {
    private final Map<String, JIngredient> keys = new HashMap<>(9, 1);
    private final Map<String, List<JIngredient>> acceptableKeys = new HashMap<>();

    public JKeys put(String key, JIngredient value) {
        this.keys.put(key, value);
        return this;
    }

    public static class Serializer implements JsonSerializer<JKeys> {
        @Override
        public JsonElement serialize(JKeys src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            src.keys.forEach((key, ingredient) -> jsonObject.add(key, context.serialize(ingredient)));
            src.acceptableKeys.forEach((key, acceptableIngredients) -> jsonObject.add(key, context.serialize(acceptableIngredients)));
            return jsonObject;
        }
    }
}
