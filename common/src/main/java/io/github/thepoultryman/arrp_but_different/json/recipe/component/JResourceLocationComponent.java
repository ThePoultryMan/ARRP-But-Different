package io.github.thepoultryman.arrp_but_different.json.recipe.component;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.minecraft.resources.ResourceLocation;

import java.lang.reflect.Type;

public class JResourceLocationComponent extends AbstractJComponent {
    public final ResourceLocation resourceLocation;

    public JResourceLocationComponent(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    public static class Serializer implements JsonSerializer<JResourceLocationComponent> {
        @Override
        public JsonElement serialize(JResourceLocationComponent src, Type type, JsonSerializationContext context) {
            return context.serialize(src.resourceLocation);
        }
    }
}
