package io.github.thepoultryman.arrp_neoforge.json.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.github.thepoultryman.arrp_neoforge.util.BaseCloneable;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JIngredient extends BaseCloneable<JIngredient> {
    private String item;
    private String tag;
    List<JIngredient> ingredients;

    public JIngredient item(String item) {
        if (this.isDefined()) {
            return this.add(new JIngredient().item(item));
        }
        this.item = item;
        return this;
    }

    public JIngredient item(Item item) {
        return this.item(BuiltInRegistries.ITEM.getKey(item).toString());
    }

    public JIngredient tag(String tag) {
        if (this.isDefined()) {
            return this.add(new JIngredient().tag(tag));
        }
        this.tag = tag;
        return this;
    }

    public JIngredient add(JIngredient ingredient) {
        if (this.ingredients == null) {
            List<JIngredient> ingredients = new ArrayList<>();
            if (this.isDefined()) {
                ingredients.add(this.clone());
            }

            this.ingredients.add(ingredient);
        }

        return this;
    }

    private boolean isDefined() {
        return this.item != null || this.tag != null;
    }

    public static class Serializer implements JsonSerializer<JIngredient> {
        @Override
        public JsonElement serialize(JIngredient src, Type typeOfSrc, JsonSerializationContext context) {
            if (src.ingredients != null) {
                return context.serialize(src.ingredients);
            }

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("item", context.serialize(src.item));
            jsonObject.add("tag", context.serialize(src.tag));
            return jsonObject;
        }
    }
}
