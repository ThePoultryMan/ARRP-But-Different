package io.github.thepoultryman.arrp_neoforge.json.recipe;

public class JSmithingRecipe extends AbstractJRecipe {
    private JIngredient base;
    private JIngredient addition;
    private JIngredient template;

    public JSmithingRecipe base(JIngredient base) {
        this.base = base;
        return this;
    }

    /**
     * Adds the "Trimmable Armor" (#minecraft:trimmable_armor) tag as the base.
     * @return The current instance of {@link JSmithingRecipe}
     */
    public JSmithingRecipe trimmableArmor() {
        return this.base(new JIngredient().tag("minecraft:trimmable_armor"));
    }

    public JSmithingRecipe() {
        super("minecraft:smithing_trim");
    }

    public JSmithingRecipe addition(JIngredient addition) {
        this.addition = addition;
        return this;
    }

    public JSmithingRecipe template(JIngredient template) {
        this.template = template;
        return this;
    }
}
