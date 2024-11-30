package io.github.thepoultryman.arrp_neoforge.json.recipe;

public class JStoneCuttingRecipe extends JRecipe {
    private final JIngredient ingredient;
    private final String result;
    private final int count;

    JStoneCuttingRecipe(JIngredient ingredient, JStackedResult result) {
        super("stonecutting");
        this.ingredient = ingredient;
        this.result = result.item;
        this.count = result.count;
    }

    @Override
    public JStoneCuttingRecipe group(final String group) {
        return (JStoneCuttingRecipe) super.group(group);
    }

    @Override
    public JStoneCuttingRecipe clone() {
        return (JStoneCuttingRecipe) super.clone();
    }
}
