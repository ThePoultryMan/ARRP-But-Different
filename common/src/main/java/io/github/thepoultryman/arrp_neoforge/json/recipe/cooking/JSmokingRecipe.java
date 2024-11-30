package io.github.thepoultryman.arrp_neoforge.json.recipe.cooking;

import io.github.thepoultryman.arrp_neoforge.json.recipe.JIngredient;
import io.github.thepoultryman.arrp_neoforge.json.recipe.JResult;

public class JSmokingRecipe extends JCookingRecipe {
    JSmokingRecipe(JIngredient ingredient, JResult result) {
        super("smoking", ingredient, result);
    }

    @Override
    public JSmokingRecipe experience(final float experience) {
        return (JSmokingRecipe) super.experience(experience);
    }

    @Override
    public JSmokingRecipe cookingTime(final int ticks) {
        return (JSmokingRecipe) super.cookingTime(ticks);
    }

    @Override
    public JSmokingRecipe group(final String group) {
        return (JSmokingRecipe) super.group(group);
    }

    @Override
    public JSmokingRecipe clone() {
        return (JSmokingRecipe) super.clone();
    }
}
