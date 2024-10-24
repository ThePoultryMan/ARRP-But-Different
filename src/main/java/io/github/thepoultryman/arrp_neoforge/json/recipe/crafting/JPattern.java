package io.github.thepoultryman.arrp_neoforge.json.recipe.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.github.thepoultryman.arrp_neoforge.util.BaseCloneable;

import java.lang.reflect.Type;

public class JPattern extends BaseCloneable<JPattern> {
    private final String[] rows;

    private JPattern(String... rows) {
        this.rows = rows;
    }

    public JPattern row(int row, String keys) {
        this.rows[row] = keys;
        return this;
    }

    public static class Serializer implements JsonSerializer<JPattern> {
        @Override
        public JsonElement serialize(JPattern src, Type typeOfSrc, JsonSerializationContext context) {
            return context.serialize(src.rows);
        }
    }
}
