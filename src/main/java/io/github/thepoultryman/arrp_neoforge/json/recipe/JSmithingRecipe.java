package io.github.thepoultryman.arrp_neoforge.json.recipe;

public class JSmithingRecipe extends JResultRecipe {
    private final JIngredient base;
    private final JIngredient addition;

    private JSmithingRecipe(JIngredient base, JIngredient addition, JResult result) {
        super("smithing", result);
        this.base = base;
        this.addition = addition;
    }

    @Override
    public JSmithingRecipe group(String group) {
        return (JSmithingRecipe) super.group(group);
    }

    @Override
    public JSmithingRecipe clone() {
        return (JSmithingRecipe) super.clone();
    }
}
