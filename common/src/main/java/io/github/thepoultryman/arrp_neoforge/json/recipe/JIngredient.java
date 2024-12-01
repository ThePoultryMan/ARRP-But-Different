package io.github.thepoultryman.arrp_neoforge.json.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.github.thepoultryman.arrp_neoforge.json.JTag;
import io.github.thepoultryman.arrp_neoforge.util.BaseCloneable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JIngredient extends BaseCloneable<JIngredient> {
    private final List<String> entries = new ArrayList<>();
    private JTag tag;

    public JIngredient entry(String entry) {
        if (this.tag != null) {
            throw new IllegalStateException("You cannot have both tags and items in an ingredient");
        }
        this.entries.add(entry);
        return this;
    }

    public JIngredient tag(JTag tag) {
        if (!this.entries.isEmpty()) {
            throw new IllegalStateException("You cannot have both tags and items in an ingredient.");
        }
        this.tag = tag;
        return this;
    }

    public static class Serializer implements JsonSerializer<JIngredient> {
        @Override
        public JsonElement serialize(JIngredient src, Type typeOfSrc, JsonSerializationContext context) {
            return context.serialize(Objects.requireNonNullElseGet(src.tag, () -> src.entries));
        }
    }
}
