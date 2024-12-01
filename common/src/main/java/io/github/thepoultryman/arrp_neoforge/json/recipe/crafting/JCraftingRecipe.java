package io.github.thepoultryman.arrp_neoforge.json.recipe.crafting;

import io.github.thepoultryman.arrp_neoforge.json.recipe.JRecipe;
import io.github.thepoultryman.arrp_neoforge.json.recipe.JResult;

public abstract class JCraftingRecipe<T extends JCraftingRecipe<T>> extends JRecipe {
    private String group;
    private JResult result;

    public JCraftingRecipe(String type) {
        super(type);
    }

    public T group(String group) {
        this.group = group;
        return (T) this;
    }

    public T result(JResult result) {
        this.result = result;
        return (T) this;
    }
}
