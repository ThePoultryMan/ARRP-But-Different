package io.github.thepoultryman.arrp_but_different.json.recipe.component;

import com.google.gson.annotations.SerializedName;
import io.github.thepoultryman.arrp_but_different.json.recipe.component.consumable.JSound;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class JEquippableComponent extends AbstractJComponent {
    private final Slot slot;
    @SerializedName("equip_sound")
    private JSound equipSound;
    @SerializedName("asset_id")
    private ResourceLocation assetId;
    @SerializedName("camera_overlay")
    private ResourceLocation cameraOverlay;
    @SerializedName("allowed_entities")
    private List<ResourceLocation> allowedEntities;
    private Boolean dispensable;
    private Boolean swappable;
    @SerializedName("damage_on_hurt")
    private Boolean damageOnHurt;
    @SerializedName("equip_on_interact")
    private Boolean equipOnInteract;

    public JEquippableComponent(Slot slot) {
        this.slot = slot;
    }

    public JEquippableComponent equipSound(JSound equipSound) {
        this.equipSound = equipSound;
        return this;
    }

    public JEquippableComponent assetId(ResourceLocation assetId) {
        this.assetId = assetId;
        return this;
    }

    public JEquippableComponent cameraOverlay(ResourceLocation cameraOverlay) {
        this.cameraOverlay = cameraOverlay;
        return this;
    }

    public JEquippableComponent allowedEntity(ResourceLocation entity) {
        if (this.allowedEntities == null) {
            this.allowedEntities = new ArrayList<>();
        }
        this.allowedEntities.add(entity);
        return this;
    }

    public JEquippableComponent dispensable(boolean dispensable) {
        this.dispensable = dispensable;
        return this;
    }

    public JEquippableComponent swappable(boolean swappable) {
        this.swappable = swappable;
        return this;
    }

    public JEquippableComponent damageOnHurt(boolean damageOnHurt) {
        this.damageOnHurt = damageOnHurt;
        return this;
    }

    public JEquippableComponent equipOnInteract(boolean equipOnInteract) {
        this.equipOnInteract = equipOnInteract;
        return this;
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
        @SerializedName("body")
        Body,
        @SerializedName("saddle")
        Saddle
    }
}
