package io.github.thepoultryman.arrp_but_different.json.recipe;

import io.github.thepoultryman.arrp_but_different.json.recipe.component.*;
import io.github.thepoultryman.arrp_but_different.util.BaseCloneable;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
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
        this.components.put(name, new JIntegerComponent(value));
        return this;
    }

    // Helpers for adding specific Components
    public JResult attributeModifiers(JAttributeModifiersComponent component) {
        return this.component("minecraft:attribute_modifiers", component);
    }

    public JResult bannerPatterns(JBannerPatternsComponent component) {
        return this.component("minecraft:banner_patterns", component);
    }

    public JResult baseColor(JBaseColorComponent component) {
        return this.component("minecraft:base_color", component);
    }

    public JResult bees(JBeesComponent component) {
        return this.component("minecraft:bees", component);
    }

    public JResult blockEntityData(JResourceLocationComponent component) {
        return this.component("minecraft:block_entity_data", component);
    }

    public JResult blockState(JBlockStateComponent component) {
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

    public JResult chargedProjectiles(JItemListComponent component) {
        return this.component("minecraft:charged_projectiles", component);
    }

    public JResult removedComponent(String name) {
        return this.component("!" + name, new EmptyJComponent());
    }
}
