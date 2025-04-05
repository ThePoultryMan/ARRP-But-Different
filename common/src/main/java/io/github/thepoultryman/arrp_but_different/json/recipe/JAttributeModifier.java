package io.github.thepoultryman.arrp_but_different.json.recipe;

import com.google.gson.annotations.SerializedName;
import net.minecraft.resources.ResourceLocation;

public class JAttributeModifier {
    private ResourceLocation type;
    private ResourceLocation id;
    private int amount = 0;
    private Operation operation;
    private Slot slot;

    public JAttributeModifier type(ResourceLocation type) {
        this.type = type;
        return this;
    }

    public JAttributeModifier id(ResourceLocation id) {
        this.id = id;
        return this;
    }

    public JAttributeModifier amount(int amount) {
        this.amount = amount;
        return this;
    }

    public JAttributeModifier operation(Operation operation) {
        this.operation = operation;
        return this;
    }

    public JAttributeModifier slot(Slot slot) {
        this.slot = slot;
        return this;
    }

    public enum Operation {
        @SerializedName("add_value")
        AddValue,
        @SerializedName("add_multiplied_base")
        AddMultipliedBase,
        @SerializedName("add_multiplied_total")
        AddMultipliedTotal,
    }

    public enum Slot {
        @SerializedName("mainhand")
        Mainhand,
        @SerializedName("offhand")
        Offhand,
        @SerializedName("head")
        Head,
        @SerializedName("chest")
        Chest,
        @SerializedName("legs")
        Legs,
        @SerializedName("feet")
        Feet,
        @SerializedName("hand")
        Hand,
        @SerializedName("armor")
        Armor,
        @SerializedName("any")
        Any,
        @SerializedName("body")
        Body
    }
}
