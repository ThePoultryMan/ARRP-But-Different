package io.github.thepoultryman.arrp_neoforge.json.recipe;

public class JResultRecipe extends JRecipe {
    private final JResult result;

    protected JResultRecipe(String type, JResult result) {
        super(type);
        this.result = result;
    }

    @Override
    public JResultRecipe group(String group) {
        return (JResultRecipe) super.group(group);
    }

    @Override
    public JResultRecipe clone() {
        return (JResultRecipe) super.clone();
    }
}
