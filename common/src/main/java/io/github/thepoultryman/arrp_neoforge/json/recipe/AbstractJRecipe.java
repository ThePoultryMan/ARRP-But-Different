package io.github.thepoultryman.arrp_neoforge.json.recipe;

import io.github.thepoultryman.arrp_neoforge.util.BaseCloneable;

public class AbstractJRecipe extends BaseCloneable<AbstractJRecipe> {
    private final String type;

    public AbstractJRecipe(String type) {
        this.type = type;
    }
}
