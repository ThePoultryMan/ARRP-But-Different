package io.github.thepoultryman.arrp_neoforge.json.recipe;

import io.github.thepoultryman.arrp_neoforge.util.BaseCloneable;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

public class JResult extends BaseCloneable<JResult> {
    public final String item;

    JResult(String item) {
        this.item = item;
    }

    public JResult(Item item) {
        this.item = BuiltInRegistries.ITEM.getKey(item).toString();
    }

    public JStackedResult stackedResult(String item, int count) {
        JStackedResult result = new JStackedResult(item);
        result.count = count;
        return result;
    }

    public JStackedResult stackedResult(Item item, int count) {
        JStackedResult result = new JStackedResult(BuiltInRegistries.ITEM.getKey(item).toString());
        result.count = count;
        return result;
    }
}
