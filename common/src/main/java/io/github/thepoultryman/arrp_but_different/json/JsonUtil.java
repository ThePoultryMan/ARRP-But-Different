package io.github.thepoultryman.arrp_but_different.json;

import com.google.gson.JsonArray;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;

import java.util.List;

public class JsonUtil {
    public static <T> JsonArray serializeCodecList(List<T> list, Codec<T> codec) {
        JsonArray array = new JsonArray(list.size());
        for (T item : list) {
            array.add(codec.encodeStart(JsonOps.INSTANCE, item).getOrThrow());
        }
        return array;
    }
}
