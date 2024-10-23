package io.github.thepoultryman.arrp_neoforge.json.state;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.minecraft.util.StringRepresentable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JVariant implements Cloneable {
    private final Map<String, List<JBlockModel>> models = new HashMap<>();

    public JVariant put(String key, JBlockModel model) {
        List<JBlockModel> models = this.models.getOrDefault(key, new ArrayList<>());
        models.add(model);
        this.models.put(key, models);
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

    public static class Serializer implements JsonSerializer<JVariant> {
        @Override
        public JsonElement serialize(JVariant src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            src.models.forEach((key, model) -> jsonObject.add(
                    key,
                    context.serialize(model.size() == 1 ? model.getFirst() : model))
            );
            return jsonObject;
        }
    }
}
