package io.github.thepoultryman.arrp_neoforge.json.state;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.minecraft.resources.ResourceLocation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JState {
    private final List<JVariant> variants = new ArrayList<>();
    private final List<JMultipart> multiparts = new ArrayList<>();

    public static JState state(JVariant... variants) {
        JState state = new JState();
        state.addAll(variants);
        return state;
    }

    public static JState state(JMultipart... parts) {
        JState state = new JState();
        state.addAll(parts);
        return state;
    }

    public JState add(JVariant variant) {
        if (!this.multiparts.isEmpty()) {
            throw new IllegalStateException("BlockStates are not allowed to have both multiparts and variants.");
        }
        this.variants.add(variant);
        return this;
    }

    public JState add(JMultipart part) {
        if (!this.variants.isEmpty()) {
            throw new IllegalStateException("BlockStates are not allowed to have both multiparts and variants.");
        }
        this.multiparts.add(part);
        return this;
    }

    public JState addAll(JVariant... variants) {
        if (!this.multiparts.isEmpty()) {
            throw new IllegalStateException("BlockStates are not allowed to have both multiparts and variants.");
        }
        this.variants.addAll(List.of(variants));
        return this;
    }

    public JState addAll(JMultipart... parts) {
        if (!this.variants.isEmpty()) {
            throw new IllegalStateException("BlockStates are not allowed to have both multiparts and variants.");
        }
        this.multiparts.addAll(List.of(parts));
        return this;
    }

    public static JVariant variant() {
        return new JVariant();
    }

    public static JVariant variant(JBlockModel model) {
        return new JVariant().put("", model);
    }

    public static JBlockModel model(String model) {
        return new JBlockModel(model);
    }

    public static JBlockModel model(ResourceLocation model) {
        return new JBlockModel(model);
    }

    public static JMultipart multipart(JBlockModel... models) {
        return new JMultipart().addAllModels(models);
    }

    public static JWhen when() {
        return new JWhen();
    }

    public static JWhen.StateBuilder whenStateBuilder() {
        return new JWhen.StateBuilder();
    }

    public JState clone() {
        try {
            return (JState) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    public static class Serializer implements JsonSerializer<JState> {
        @Override
        public JsonElement serialize(JState src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            if (!src.variants.isEmpty()) {
                if (src.variants.size() == 1) {
                    jsonObject.add("variants", context.serialize(src.variants.getFirst()));
                } else {
                    jsonObject.add("variants", context.serialize(src.variants));
                }
            }
            if (!src.multiparts.isEmpty()) {
                jsonObject.add("multipart", context.serialize(src.multiparts));
            }
            return jsonObject;
        }
    }
}
