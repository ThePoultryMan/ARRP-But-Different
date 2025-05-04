package io.github.thepoultryman.arrp_but_different.json.recipe.crafting;

import io.github.thepoultryman.arrp_but_different.json.recipe.JIngredient;

public class JTransmuteRecipe extends AbstractJCraftingRecipe<JTransmuteRecipe> {
    private String category;
    private JIngredient input;
    private JIngredient material;

    public JTransmuteRecipe(String type) {
        super("minecraft:crafting_transmute");
    }

    public JTransmuteRecipe category(String category) {
        this.category = category;
        return this;
    }

    public JTransmuteRecipe input(JIngredient input) {
        this.input = input;
        return this;
    }

    public JTransmuteRecipe material(JIngredient material) {
        this.material = material;
        return this;
    }
}
