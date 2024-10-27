package io.github.thepoultryman.arrp_neoforge.json.state;

import net.minecraft.util.StringRepresentable;

import java.util.ArrayList;
import java.util.List;

public class JVariant implements Cloneable {
    final List<String> conditions = new ArrayList<>();
    final List<JBlockModel> models = new ArrayList<>();

    public JVariant put(String key, JBlockModel model) {
        this.conditions.add(key);
        this.models.add(model);
        return this;
    }

    private <T> JVariant putFormatted(String property, T value, JBlockModel model) {
        return this.put(property + "=" + value, model);
    }

    public JVariant put(String property, boolean value, JBlockModel model) {
        return this.putFormatted(property, value, model);
    }


    public JVariant put(String property, int value, JBlockModel model) {
        return this.putFormatted(property, value, model);
    }

    public JVariant put(String property, StringRepresentable value, JBlockModel model) {
        return this.putFormatted(property, value.getSerializedName(), model);
    }

    public JVariant put(String property, String value, JBlockModel model) {
        return this.putFormatted(property, value, model);
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
