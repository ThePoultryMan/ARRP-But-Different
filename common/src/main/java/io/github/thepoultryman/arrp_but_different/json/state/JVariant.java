package io.github.thepoultryman.arrp_but_different.json.state;

import net.minecraft.util.StringRepresentable;

import java.util.ArrayList;
import java.util.List;

public class JVariant implements Cloneable {
    final List<String> conditions = new ArrayList<>();
    JBlockModel model;

    public JVariant put(String key, JBlockModel model) {
        this.conditions.add(key);
        this.model = model;
        return this;
    }

    private <T> JVariant putFormatted(String property, T value) {
        this.conditions.add(property + "=" + value);
        return this;
    }

    public JVariant condition(String property, boolean value) {
        return this.putFormatted(property, value);
    }


    public JVariant condition(String property, int value) {
        return this.putFormatted(property, value);
    }

    public JVariant condition(String property, StringRepresentable value) {
        return this.putFormatted(property, value.getSerializedName());
    }

    public JVariant condition(String property, String value) {
        return this.putFormatted(property, value);
    }

    public JVariant model(JBlockModel model) {
        this.model = model;
        return this;
    }

    @Override
    public JVariant clone() {
        try {
            return (JVariant) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}
