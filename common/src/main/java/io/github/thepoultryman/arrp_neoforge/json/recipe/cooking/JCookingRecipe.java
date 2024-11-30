package io.github.thepoultryman.arrp_neoforge.json.recipe.cooking;

import com.google.gson.annotations.SerializedName;
import io.github.thepoultryman.arrp_neoforge.json.recipe.JIngredient;
import io.github.thepoultryman.arrp_neoforge.json.recipe.JRecipe;
import io.github.thepoultryman.arrp_neoforge.json.recipe.JResult;

public class JCookingRecipe extends JRecipe {
    private final JIngredient ingredient;
    private final String result;
    private Float experience;
    @SerializedName("cookingtime")
    private Integer cookingTime;

    public JCookingRecipe(String type, JIngredient ingredient, JResult result) {
        super(type);
        this.ingredient = ingredient;
        this.result = result.item;
    }

    public JCookingRecipe experience(float experience) {
        this.experience = experience;
        return this;
    }

    public JCookingRecipe cookingTime(int ticks) {
        this.cookingTime = ticks;
        return this;
    }

    @Override
    public JCookingRecipe group(final String group) {
        return (JCookingRecipe) super.group(group);
    }

    @Override
    public JCookingRecipe clone() {
        return (JCookingRecipe) super.clone();
    }
}
