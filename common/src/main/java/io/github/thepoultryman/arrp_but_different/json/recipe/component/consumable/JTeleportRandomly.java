package io.github.thepoultryman.arrp_but_different.json.recipe.component.consumable;

public class JTeleportRandomly extends JConsumeEffect {
    private int diameter;

    public JTeleportRandomly() {
        super("minecraft:teleport_randomly");
    }

    public JTeleportRandomly diameter(int diameter) {
        this.diameter = diameter;
        return this;
    }
}
