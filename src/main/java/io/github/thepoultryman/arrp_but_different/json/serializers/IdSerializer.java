package io.github.thepoultryman.arrp_but_different.json.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.minecraft.resources.Identifier;

import java.lang.reflect.Type;

public class IdSerializer implements JsonSerializer<Identifier> {
    @Override
    public JsonElement serialize(Identifier resourceLocation, Type type, JsonSerializationContext jsonSerializationContext) {
        return jsonSerializationContext.serialize(resourceLocation.toString());
    }
}
