package io.github.thepoultryman.arrp_but_different.json.recipe;

import io.github.thepoultryman.arrp_but_different.util.BaseCloneable;

public class AbstractJRecipe extends BaseCloneable<AbstractJRecipe> {
    private final String type;

    public AbstractJRecipe(String type) {
        this.type = type;
    }
}
