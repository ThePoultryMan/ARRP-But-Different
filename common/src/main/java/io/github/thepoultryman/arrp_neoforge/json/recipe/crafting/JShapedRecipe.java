package io.github.thepoultryman.arrp_neoforge.json.recipe.crafting;

import io.github.thepoultryman.arrp_neoforge.json.recipe.JResult;
import io.github.thepoultryman.arrp_neoforge.json.recipe.JResultRecipe;

public class JShapedRecipe extends JResultRecipe {
    private final JPattern pattern;
    private final JKeys keys;

    public JShapedRecipe(JResult result, JPattern pattern, JKeys keys) {
        super("minecraft:crafting_shaped", result);
        this.pattern = pattern;
        this.keys = keys;
    }

    @Override
    public JShapedRecipe group(final String group) {
        return (JShapedRecipe) super.group(group);
    }

    @Override
    public JShapedRecipe clone() {
        return (JShapedRecipe) super.clone();
    }
}
