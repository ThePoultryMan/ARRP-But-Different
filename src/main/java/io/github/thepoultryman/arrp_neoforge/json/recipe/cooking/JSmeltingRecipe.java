package io.github.thepoultryman.arrp_neoforge.json.recipe.cooking;

import io.github.thepoultryman.arrp_neoforge.json.recipe.JIngredient;
import io.github.thepoultryman.arrp_neoforge.json.recipe.JResult;

public class JSmeltingRecipe extends JCookingRecipe {
    JSmeltingRecipe(JIngredient ingredient, JResult result) {
        super("smelting", ingredient, result);
    }

    @Override
    public JSmeltingRecipe experience(final float experience) {
        return (JSmeltingRecipe) super.experience(experience);
    }

    @Override
    public JSmeltingRecipe cookingTime(final int ticks) {
        return (JSmeltingRecipe) super.cookingTime(ticks);
    }

    @Override
    public JSmeltingRecipe group(final String group) {
        return (JSmeltingRecipe) super.group(group);
    }

    @Override
    public JSmeltingRecipe clone() {
        return (JSmeltingRecipe) super.clone();
    }
}
