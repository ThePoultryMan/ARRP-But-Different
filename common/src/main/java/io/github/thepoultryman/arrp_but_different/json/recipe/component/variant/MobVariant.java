package io.github.thepoultryman.arrp_but_different.json.recipe.component.variant;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.animal.horse.Variant;
import net.minecraft.world.entity.animal.wolf.WolfSoundVariant;
import net.minecraft.world.entity.animal.wolf.WolfVariant;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.DyeColor;

public record MobVariant(ResourceLocation entity, String variantType, String value) {
    public MobVariant(String entity, String variantType, String value) {
        this(minecraftLocation(entity), variantType, value);
    }

    public MobVariant(String entity, String variantType, ResourceLocation value) {
        this(minecraftLocation(entity), variantType, value.toString());
    }

    public static MobVariant axolotl(Axolotl.Variant variant) {
        return new MobVariant("axolotl", "variant", variant.getName());
    }

    public static MobVariant catCollar(DyeColor color) {
        return new MobVariant("cat", "collar", color.getSerializedName());
    }

    public static MobVariant cat(ResourceKey<CatVariant> variant) {
        return new MobVariant("cat", "variant", variant.location());
    }

    public static MobVariant chicken(ResourceKey<ChickenVariant> variant) {
        return new MobVariant("chicken", "variant", variant.location());
    }

    public static MobVariant cow(ResourceKey<CowVariant> variant) {
        return new MobVariant("cow", "variant", variant.location());
    }

    public static MobVariant fox(Fox.Variant variant) {
        return new MobVariant("fox", "variant", variant.getSerializedName());
    }

    public static MobVariant frog(ResourceKey<FrogVariant> variant) {
        return new MobVariant("frog", "variant", variant.location());
    }

    public static MobVariant horse(Variant variant) {
        return new MobVariant("horse", "variant", variant.getSerializedName());
    }

    public static MobVariant llama(Llama.Variant variant) {
        return new MobVariant("llama", "variant", variant.getSerializedName());
    }

    public static MobVariant mooshroom(MushroomCow.Variant variant) {
        return new MobVariant("mooshroom", "variant", variant.getSerializedName());
    }

    public static MobVariant painting(ResourceLocation variant) {
        return new MobVariant("painting", "variant", variant);
    }

    public static MobVariant parrot(Parrot.Variant variant) {
        return new MobVariant("parrot", "variant", variant.getSerializedName());
    }

    public static MobVariant rabbit(Rabbit.Variant variant) {
        return new MobVariant("rabbit", "variant", variant.getSerializedName());
    }

    public static MobVariant salmon(Salmon.Variant size) {
        return new MobVariant("salmon", "size", size.getSerializedName());
    }

    public static MobVariant sheep(DyeColor color) {
        return new MobVariant("sheep", "color", color.getSerializedName());
    }

    public static MobVariant tropicalFishColor(DyeColor color) {
        return new MobVariant("tropical_fish", "base_color", color.getSerializedName());
    }

    public static MobVariant tropicalFishPattern(TropicalFish.Pattern pattern) {
        return new MobVariant("tropical_fish", "pattern", pattern.getSerializedName());
    }

    public static MobVariant tropicalFishPatternColor(DyeColor color) {
        return new MobVariant("tropical_fish", "pattern_color", color.getSerializedName());
    }

    public static MobVariant villager(ResourceKey<VillagerType> variant) {
        return new MobVariant("villager", "variant", variant.location());
    }

    public static MobVariant wolfCollar(DyeColor color) {
        return new MobVariant("wolf", "collar", color.getName());
    }

    public static MobVariant wolfSoundVariant(ResourceKey<WolfSoundVariant> soundVariant) {
        return new MobVariant("wolf", "sound_variant", soundVariant.location());
    }

    public static MobVariant wolf(ResourceKey<WolfVariant> variant) {
        return new MobVariant("wolf", "variant", variant.location());
    }

    private static ResourceLocation minecraftLocation(String key) {
        return ResourceLocation.withDefaultNamespace(key);
    }

    @Override
    public String toString() {
        return this.entity.toString() + "/" + this.variantType;
    }
}
