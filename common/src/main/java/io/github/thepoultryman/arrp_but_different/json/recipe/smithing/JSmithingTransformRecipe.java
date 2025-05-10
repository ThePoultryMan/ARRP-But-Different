package io.github.thepoultryman.arrp_but_different.json.recipe.smithing;

import io.github.thepoultryman.arrp_but_different.json.recipe.JResult;

public class JSmithingTransformRecipe extends AbstractJSmithingRecipe<JSmithingTransformRecipe> {
    private JResult result;

    public JSmithingTransformRecipe() {
        super("minecraft:smithing_transform");
    }

    public JSmithingTransformRecipe result(JResult result) {
        this.result = result;
        return this;
    }
}
