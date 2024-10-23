package io.github.thepoultryman.arrp_neoforge.json.state;

import net.minecraft.resources.ResourceLocation;

public class JBlockModel implements Cloneable {
    private final ResourceLocation model;
    private Integer x;
    private Integer y;
    private Boolean uvlock;
    private Integer weight;

    public JBlockModel(String model) {
        this.model = ResourceLocation.withDefaultNamespace(model);
    }

    public JBlockModel(ResourceLocation model) {
        this.model = model;
    }

    public JBlockModel x(int x) {
        this.x = x;
        return this;
    }

    public JBlockModel y(int y) {
        this.y = y;
        return this;
    }

    public JBlockModel uvlock() {
        this.uvlock = true;
        return this;
    }

    public JBlockModel weight(int weight) {
        this.weight = weight;
        return this;
    }

    @Override
    public JBlockModel clone() {
        try {
            return (JBlockModel) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}
