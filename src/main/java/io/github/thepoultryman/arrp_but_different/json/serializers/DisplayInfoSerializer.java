package io.github.thepoultryman.arrp_but_different.json.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.github.thepoultryman.arrp_but_different.json.JsonUtil;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.world.item.ItemStack;

import java.lang.reflect.Type;

public class DisplayInfoSerializer implements JsonSerializer<DisplayInfo> {
    @Override
    public JsonElement serialize(DisplayInfo src, Type type, JsonSerializationContext context) {
        JsonObject displayInfo = new JsonObject();

        displayInfo.add("icon", JsonUtil.serializeCodec(src.getIcon(), ItemStack.CODEC));
        displayInfo.add("title", context.serialize(src.getTitle()));
        displayInfo.add("description", context.serialize(src.getDescription()));
        displayInfo.add("frame", context.serialize(src.getType().getSerializedName()));
        src.getBackground().ifPresent((background) -> displayInfo.add("background", context.serialize(background.id())));
        displayInfo.add("show_toast", context.serialize(src.shouldShowToast()));
        displayInfo.add("announce_to_chat", context.serialize(src.shouldAnnounceChat()));
        displayInfo.add("hidden", context.serialize(src.isHidden()));

        return displayInfo;
    }
}
