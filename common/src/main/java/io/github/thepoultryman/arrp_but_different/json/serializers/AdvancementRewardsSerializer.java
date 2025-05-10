package io.github.thepoultryman.arrp_but_different.json.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.github.thepoultryman.arrp_but_different.json.JsonUtil;
import net.minecraft.advancements.AdvancementRewards;

import java.lang.reflect.Type;

public class AdvancementRewardsSerializer implements JsonSerializer<AdvancementRewards> {
    @Override
    public JsonElement serialize(AdvancementRewards src, Type type, JsonSerializationContext context) {
        return JsonUtil.serializeCodec(src, AdvancementRewards.CODEC);
    }
}
