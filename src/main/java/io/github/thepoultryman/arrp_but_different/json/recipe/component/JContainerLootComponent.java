package io.github.thepoultryman.arrp_but_different.json.recipe.component;

import com.google.gson.annotations.SerializedName;
import net.minecraft.resources.ResourceLocation;

public class JContainerLootComponent extends AbstractJComponent {
    @SerializedName("loot_table")
    private final ResourceLocation lootTable;
    private Integer seed;

    public JContainerLootComponent(ResourceLocation lootTable) {
        this.lootTable = lootTable;
    }

    public JContainerLootComponent seed(int seed) {
        this.seed = seed;
        return this;
    }
}
