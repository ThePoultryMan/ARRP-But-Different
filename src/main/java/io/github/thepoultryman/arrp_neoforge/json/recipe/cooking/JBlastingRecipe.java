package io.github.thepoultryman.arrp_neoforge.json.recipe.cooking;

import io.github.thepoultryman.arrp_neoforge.json.recipe.JIngredient;
import io.github.thepoultryman.arrp_neoforge.json.recipe.JResult;

public class JBlastingRecipe extends JCookingRecipe {
    public JBlastingRecipe(JIngredient ingredient, JResult result) {
        super("blasting", ingredient, result);
    }

    @Override
    public JBlastingRecipe experience(final float experience) {
        return (JBlastingRecipe) super.experience(experience);
    }

    @Override
    public JBlastingRecipe cookingTime(final int ticks) {
        return (JBlastingRecipe) super.cookingTime(ticks);
    }

    @Override
    public JBlastingRecipe group(final String group) {
        return (JBlastingRecipe) super.group(group);
    }

    @Override
    public JBlastingRecipe clone() {
        return (JBlastingRecipe) super.clone();
    }
}
