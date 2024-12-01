package io.github.thepoultryman.arrp_neoforge.json.recipe;

import io.github.thepoultryman.arrp_neoforge.util.BaseCloneable;

public class JRecipe extends BaseCloneable<JRecipe> {
    private final String type;

    public JRecipe(String type) {
        this.type = type;
    }
}
