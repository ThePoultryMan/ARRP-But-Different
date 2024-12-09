package io.github.thepoultryman.arrp_neoforge.json.recipe;

public abstract class AbstractJResultingRecipe<T> extends AbstractJRecipe {
    private JResult result;

    public AbstractJResultingRecipe(String type) {
        super(type);
    }

    public T result(JResult result) {
        this.result = result;
        return (T) this;
    }
}
