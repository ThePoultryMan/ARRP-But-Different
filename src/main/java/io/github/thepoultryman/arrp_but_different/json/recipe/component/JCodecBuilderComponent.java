package io.github.thepoultryman.arrp_but_different.json.recipe.component;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.serialization.Codec;

import java.lang.reflect.Type;

public abstract class JCodecBuilderComponent<T> extends AbstractJComponent {
    protected T object;
    final Codec<T> codec;

    public JCodecBuilderComponent(Codec<T> codec) {
        this.codec = codec;
    }

    protected T build() {
        if (this.object != null) {
            return this.object;
        } else {
            return this.manuallyBuild();
        }
    }

    public abstract T manuallyBuild();

    public static class Serializer<C, T extends JCodecBuilderComponent<C>> implements JsonSerializer<T> {
        @Override
        public JsonElement serialize(T src, Type type, JsonSerializationContext context) {
            return context.serialize(new JCodecComponent<>(src.build(), src.codec));
        }
    }
}
