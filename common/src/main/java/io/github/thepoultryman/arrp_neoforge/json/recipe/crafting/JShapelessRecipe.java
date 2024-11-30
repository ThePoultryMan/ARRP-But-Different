package io.github.thepoultryman.arrp_neoforge.json.recipe.crafting;

import io.github.thepoultryman.arrp_neoforge.json.recipe.JIngredients;
import io.github.thepoultryman.arrp_neoforge.json.recipe.JResult;
import io.github.thepoultryman.arrp_neoforge.json.recipe.JResultRecipe;

public class JShapelessRecipe extends JResultRecipe {
    private final JIngredients ingredients;

    JShapelessRecipe(JResult result, JIngredients ingredients) {
        super("minecraft:crafting_shapeless", result);
        this.ingredients = ingredients;
    }

    @Override
    public JShapelessRecipe group(final String group) {
        return (JShapelessRecipe) super.group(group);
    }

    @Override
    public JShapelessRecipe clone() {
        return (JShapelessRecipe) super.clone();
    }
}
