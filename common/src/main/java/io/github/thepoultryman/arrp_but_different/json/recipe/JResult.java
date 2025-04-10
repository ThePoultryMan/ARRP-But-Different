package io.github.thepoultryman.arrp_but_different.json.recipe;

import io.github.thepoultryman.arrp_but_different.json.PresetColor;
import io.github.thepoultryman.arrp_but_different.json.recipe.component.*;
import io.github.thepoultryman.arrp_but_different.json.recipe.component.consumable.JConsumableComponent;
import io.github.thepoultryman.arrp_but_different.json.recipe.component.variant.*;
import io.github.thepoultryman.arrp_but_different.util.BaseCloneable;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.LockCode;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.*;
import net.minecraft.world.item.enchantment.Repairable;
import net.minecraft.world.level.block.entity.PotDecorations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JResult extends BaseCloneable<JResult> {
    private ResourceLocation id;
    private int count;
    // TODO: Add builders for all vanilla components
    private final Map<String, AbstractJComponent> components = new HashMap<>();

    public JResult id(ResourceLocation location) {
        this.id = location;
        return this;
    }

    public JResult count(int count) {
        this.count = count;
        return this;
    }

    // Allow inputting specific components.
    public JResult component(String name, AbstractJComponent value) {
        this.components.put(name, value);
        return this;
    }

    public JResult component(String name, int value) {
        this.components.put(name, new JSimpleComponent<>(value));
        return this;
    }

    // Helpers for adding specific Components
    public JResult attributeModifiers(JAttributeModifiersComponent component) {
        return this.component("minecraft:attribute_modifiers", component);
    }

    public JResult bannerPatterns(JBannerPatternsComponent component) {
        return this.component("minecraft:banner_patterns", component);
    }

    public JResult baseColor(JColorComponent component) {
        return this.component("minecraft:base_color", component);
    }

    public JResult bees(JBeesComponent component) {
        return this.component("minecraft:bees", component);
    }

    public JResult blockEntityData(ResourceLocation resourceLocation) {
        return this.component("minecraft:block_entity_data", new JSimpleComponent<>(resourceLocation));
    }

    public JResult blockState(JStringMapComponent component) {
        return this.component("minecraft:block_state", component);
    }

    public JResult bucketEntityData(JBucketEntityDataComponent component) {
        return this.component("minecraft:bucket_entity_data", component);
    }

    public JResult bundleContents(JItemListComponent component) {
        return this.component("minecraft:bundle_contents", component);
    }

    public JResult canBreak(JPredicateListComponent component) {
        return this.component("minecraft:can_break", component);
    }

    public JResult canPlaceOn(JPredicateListComponent component) {
        return this.component("minecraft:can_place_on", component);
    }

    public JResult catCollar(PresetColor color) {
        return this.component("minecraft:cat/collar", new JColorComponent(color));
    }

    public JResult chargedProjectiles(JItemListComponent component) {
        return this.component("minecraft:charged_projectiles", component);
    }

    public JResult consumable(JConsumableComponent component) {
        return this.component("minecraft:consumable", component);
    }

    public JResult container(JContainerComponent component) {
        return this.component("minecraft:container", component);
    }

    public JResult containerLoot(JContainerLootComponent component) {
        return this.component("minecraft:container_loot", component);
    }

    public JResult creativeSlotLock(JMultitypeComponent component) {
        return this.component("minecraft:creative_slot_lock", component);
    }

    public JResult customData(JCustomDataComponent component) {
        return this.component("minecraft:custom_data", component);
    }

    public JResult customModelData(JCustomModelDataComponent component) {
        return this.component("minecraft:custom_model_data", component);
    }

    public JResult customName(String string) {
        return this.component("minecraft:custom_name", new JSimpleComponent<>(string));
    }

    public JResult damage(int damage) {
        return this.component("minecraft:damage", damage);
    }

    public JResult damageResistance(ResourceLocation types) {
        return this.component("minecraft:damage_resistant", new JDamageResistanceComponent(types));
    }

    public JResult deathProtection(JDeathProtectionComponent component) {
        return this.component("minecraft:death_protection", component);
    }

    public JResult debugStickState(JStringMapComponent component) {
        return this.component("minecraft:debug_stick_state", component);
    }

    public JResult dyedColor(JDyedColorComponent component) {
        return this.component("minecraft:dyed_color", component);
    }

    public JResult enchantable(int value) {
        return this.component("minecraft:enchantable", new JSimpleComponent<>(value));
    }

    public JResult enchantmentGlintOverride(boolean override) {
        return this.component("minecraft:enchantment_glint_override", new JSimpleComponent<>(override));
    }

    public JResult enchantments(JEnchantmentsComponent component) {
        return this.component("minecraft:enchantments", component);
    }

    public JResult entityData(ResourceLocation id) {
        return this.component("minecraft:entity_data", new JIdResourceLocationComponent(id));
    }

    public JResult equippable(JEquippableComponent component) {
        return this.component("minecraft:equippable", component);
    }

    public JResult fireworkExplosion(JFireworkExplosionComponent component) {
        return this.component("minecraft:firework_explosion", component);
    }

    public JResult fireworks(JFireworksComponent component) {
        return this.component("minecraft:fireworks", component);
    }

    public JResult food(JFoodComponent component) {
        return this.component("minecraft:food", component);
    }

    public JResult glider() {
        return this.component("minecraft:glider", new EmptyJComponent());
    }

    public JResult instrument(Instrument instrument) {
        return this.component("minecraft:instrument", new JCodecComponent<>(instrument, Instrument.DIRECT_CODEC));
    }

    public JResult intangibleProjectile() {
        return this.component("minecraft:intangible_projectile", new EmptyJComponent());
    }

    public JResult itemModel(ResourceLocation modelLocation) {
        return this.component("minecraft:item_model", new JSimpleComponent<>(modelLocation));
    }

    public JResult itemName(Component component) {
        return this.component("minecraft:item_name", new JCodecComponent<>(component, ComponentSerialization.CODEC));
    }

    public JResult jukeboxPlayable(ResourceLocation resourceLocation) {
        return this.component("minecraft:jukebox_playable", new JSimpleComponent<>(resourceLocation));
    }

    public JResult lock(LockCode lockCode) {
        return this.component("minecraft:lock", new JCodecComponent<>(lockCode, LockCode.CODEC));
    }

    public JResult lodestoneTracker(LodestoneTracker lodestoneTracker) {
        return this.component("minecraft:lodestone_tracker", new JCodecComponent<>(lodestoneTracker, LodestoneTracker.CODEC));
    }

    public JResult lore(Component... components) {
        List<JCodecComponent<Component>> codecComponents = new ArrayList<>(components.length);
        for (int i = 0; i < components.length; i++) {
            codecComponents.add(i, new JCodecComponent<>(components[i], ComponentSerialization.CODEC));
        }
        return this.component("minecraft:lore", new JSimpleComponent<>(codecComponents));
    }

    public JResult mapColor(int mapColor) {
        return this.component("minecraft:map_color", new JSimpleComponent<>(mapColor));
    }

    public JResult mapDecorations(MapDecorations mapDecorations) {
        return this.component("minecraft:map_decorations", new JCodecComponent<>(mapDecorations, MapDecorations.CODEC));
    }

    public JResult mapId(int mapId) {
        return this.component("minecraft:map_id", new JSimpleComponent<>(mapId));
    }

    public JResult mapPostProcessing(MapPostProcessing mapPostProcessing) {
        return this.component("minecraft:map_post_processing", new JSimpleComponent<>(mapPostProcessing));
    }

    public JResult maxDamage(int maxDamage) {
        return this.component("minecraft:max_damage", new JSimpleComponent<>(maxDamage));
    }

    public JResult maxStackSize(int maxStackSize) {
        return this.component("minecraft:max_stack_size", new JSimpleComponent<>(maxStackSize));
    }

    public JResult noteBlockSound(ResourceLocation noteBlockSound) {
        return this.component("minecraft:note_block_sound", new JSimpleComponent<>(noteBlockSound));
    }

    public JResult ominousBottleAmplifier(int amplifier) {
        return this.component("minecraft:ominous_bottle_amplifier", new JSimpleComponent<>(amplifier));
    }

    public JResult potDecorations(PotDecorations decorations) {
        return this.component("minecraft:pot_decorations", new JCodecComponent<>(decorations, PotDecorations.CODEC));
    }

    public JResult potionContents(PotionContents contents) {
        return this.component("minecraft:potion_contents", new JCodecComponent<>(contents, PotionContents.CODEC));
    }

    public JResult potionDurationScale(float durationScale) {
        return this.component("minecraft:potion_duration_scale", new JSimpleComponent<>(durationScale));
    }

    public JResult profile(ResolvableProfile profile) {
        return this.component("minecraft:profile", new JCodecComponent<>(profile, ResolvableProfile.CODEC));
    }

    public JResult providesBannerPatterns(ResourceLocation tag) {
        return this.component("minecraft:provides_banner_pattern", new JSimpleComponent<>("#" + tag.toString()));
    }

    public JResult providesTrimMaterial(ProvidesTrimMaterial material) {
        return this.component("minecraft:provides_trim_material", new JCodecComponent<>(material, ProvidesTrimMaterial.CODEC));
    }

    public JResult rarity(Rarity rarity) {
        return this.component("minecraft:rarity", new JCodecComponent<>(rarity, Rarity.CODEC));
    }

    public JResult recipes(JListComponent<ResourceLocation> component) {
        return this.component("minecraft:recipes", component);
    }

    public JResult repairable(Repairable repairable) {
        return this.component("minecraft:repairable", new JCodecComponent<>(repairable, Repairable.CODEC));
    }

    public JResult repairCost(int cost) {
        return this.component("minecraft:repair_cost", new JSimpleComponent<>(cost));
    }

    public JResult storedEnchantments(JListComponent<ResourceLocation> enchantments) {
        return this.component("minecraft:stored_enchantments", enchantments);
    }

    public JResult suspiciousStewEffects(JListComponent<JSuspiciousStewEffect> effects) {
        return this.component("minecraft:suspicious_stew_effects", effects);
    }

    public JResult catVariant(ResourceLocation resourceLocation) {
        return this.component("minecraft:cat/variant", new JSimpleComponent<>(resourceLocation));
    }

    public JResult chickenVariant(ResourceLocation resourceLocation) {
        return this.component("minecraft:chicken/variant", new JSimpleComponent<>(resourceLocation));
    }

    public JResult cowVariant(ResourceLocation resourceLocation) {
        return this.component("minecraft:cow/variant", new JSimpleComponent<>(resourceLocation));
    }

    public JResult foxVariant(FoxVariant variant) {
        return this.component("minecraft:fox/variant", new JSimpleComponent<>(variant));
    }

    public JResult frogVariant(FrogVariant variant) {
        return this.component("minecraft:frog/variant", new JSimpleComponent<>(variant));
    }

    public JResult horseVariant(HorseVariant variant) {
        return this.component("minecraft:horse/variant", new JSimpleComponent<>(variant));
    }

    public JResult llamaVariant(LlamaVariant variant) {
        return this.component("minecraft:llama/variant", new JSimpleComponent<>(variant));
    }

    public JResult mooshroomVariant(MooshroomVariant variant) {
        return this.component("minecraft:mooshroom/variant", new JSimpleComponent<>(variant));
    }

    public JResult removedComponent(String name) {
        return this.component("!" + name, new EmptyJComponent());
    }
}
