package io.github.thepoultryman.arrp_neoforge.json.recipe.crafting;

import io.github.thepoultryman.arrp_neoforge.json.recipe.JIngredient;

import java.util.ArrayList;
import java.util.List;

public class JShapelessRecipe extends AbstractJCraftingRecipe<JShapelessRecipe> {
    private final List<JIngredient> ingredients = new ArrayList<>();

    public JShapelessRecipe() {
        super("minecraft:crafting_shapeless");
    }

    public JShapelessRecipe ingredient(JIngredient ingredient) {
        this.ingredients.add(ingredient);
        return this;
    }

    public JShapelessRecipe ingredients(JIngredient... ingredients) {
        this.ingredients.addAll(List.of(ingredients));
        return this;
    }
}
