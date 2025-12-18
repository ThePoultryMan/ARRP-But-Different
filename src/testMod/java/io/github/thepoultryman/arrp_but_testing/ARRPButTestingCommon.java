package io.github.thepoultryman.arrp_but_testing;

import io.github.thepoultryman.arrp_but_different.api.RuntimeResourcePack;
import io.github.thepoultryman.arrp_but_different.json.JLang;
import io.github.thepoultryman.arrp_but_different.json.JTag;
import io.github.thepoultryman.arrp_but_different.json.advancement.DisplayInfoBuilder;
import io.github.thepoultryman.arrp_but_different.json.advancement.JAdvancement;
import io.github.thepoultryman.arrp_but_different.json.model.JElement;
import io.github.thepoultryman.arrp_but_different.json.model.JFace;
import io.github.thepoultryman.arrp_but_different.json.model.JFaces;
import io.github.thepoultryman.arrp_but_different.json.model.JModel;
import io.github.thepoultryman.arrp_but_different.json.recipe.JIngredient;
import io.github.thepoultryman.arrp_but_different.json.recipe.JResult;
import io.github.thepoultryman.arrp_but_different.json.recipe.crafting.JShapedRecipe;
import io.github.thepoultryman.arrp_but_different.json.recipe.crafting.JShapelessRecipe;
import io.github.thepoultryman.arrp_but_different.json.recipe.smelting.JSmeltingRecipe;
import io.github.thepoultryman.arrp_but_different.json.recipe.smelting.SmeltingTypes;
import io.github.thepoultryman.arrp_but_different.json.recipe.smithing.JSmithingTrimRecipe;
import io.github.thepoultryman.arrp_but_different.json.state.JBlockModel;
import io.github.thepoultryman.arrp_but_different.json.state.JState;
import io.github.thepoultryman.arrp_but_different.json.state.JVariant;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.DataComponentMatchers;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemLore;

import java.util.List;

public class ARRPButTestingCommon {
    public static final String MOD_ID = "arrp_but_testing";

    public static RuntimeResourcePack beforeUserEvent() {
        RuntimeResourcePack pack = RuntimeResourcePack.create(Identifier.fromNamespaceAndPath(MOD_ID, "before_user"));
        pack.addLang(Identifier.fromNamespaceAndPath(MOD_ID, "en_us"),
                new JLang().addBlockTranslation(Identifier.withDefaultNamespace("torch"), "Torch but it's different but it's not so it's the same")
                        .addItemTranslation(Items.STICK, "It's still a stick")
        );
        pack.addBlockSate(Identifier.withDefaultNamespace("acacia_door"),
                new JState().add(
                        new JVariant()
                                .condition("facing", "east")
                                .condition("half", "lower")
                                .condition("hinge", "left")
                                .condition("open", "false")
                                .model(new JBlockModel(Identifier.withDefaultNamespace("block/spruce_door_bottom_left")))
                )
        );
        pack.addRecipe(Identifier.fromNamespaceAndPath(MOD_ID, "pumpkin"),
                new JShapedRecipe()
                        .row(0, "PPP")
                        .row(1, "P P")
                        .row(2, "PPP")
                        .key("P", new JIngredient()
                                .entry("minecraft:pumpkin_pie")
                        )
                        .result(
                                new JResult()
                                        .id(Identifier.withDefaultNamespace("pumpkin"))
                                        .count(3)
                        )
        );
        pack.addRecipe(
                Identifier.fromNamespaceAndPath(MOD_ID, "golden_sword"),
                new JShapelessRecipe()
                        .ingredient(
                                new JIngredient().entry("minecraft:stick")
                        )
                        .ingredients(
                                new JIngredient().entry("minecraft:gold_ingot"),
                                new JIngredient().entry("minecraft:gold_ingot"),
                                new JIngredient().entry("minecraft:gold_ingot")
                        )
                        .result(
                                new JResult()
                                        .id(Identifier.withDefaultNamespace("golden_sword"))
                                        .component("minecraft:damage", 3)
                                        .rarity(Rarity.RARE)
                        )
        );
        Component burntBreadName = Component.literal("Burnt Bread").setStyle(Style.EMPTY.applyFormat(ChatFormatting.BOLD));
        List<Component> burntBreadLore = List.of(Component.literal("A burnt piece of bread"), Component.literal("Does nothing"));
        pack.addRecipe(
                Identifier.fromNamespaceAndPath(MOD_ID, "burnt_bread"),
                new JSmeltingRecipe(SmeltingTypes.BLASTING)
                        .ingredient(
                                new JIngredient().entry("minecraft:bread")
                        )
                        .cookingTime(30)
                        .result(
                                new JResult()
                                        .id(Identifier.withDefaultNamespace("coal"))
                                        .itemName(burntBreadName)
                                        .lore(burntBreadLore.getFirst())
                        )
        );
        pack.addAdvancement(Identifier.fromNamespaceAndPath(MOD_ID, "root"),
                new JAdvancement()
                        .display(new DisplayInfoBuilder()
                                .icon(Items.BREAD)
                                .title(Component.literal("Cooked Bread?"))
                                .description(Component.literal("Burn a piece of bread. Congratulations?"))
                                .type(AdvancementType.GOAL)
                                .build()
                        )
                        .criteria("burn_bread",
                                InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
                                        .of(BuiltInRegistries.ITEM, Items.BREAD)
                                        .withComponents(
                                                DataComponentMatchers.Builder.components()
                                                        .exact(
                                                                DataComponentExactPredicate.expect(
                                                                        DataComponents.ITEM_NAME,
                                                                        burntBreadName
                                                                )
                                                        )
                                                        .exact(
                                                                DataComponentExactPredicate.expect(
                                                                        DataComponents.LORE,
                                                                        ItemLore.EMPTY
                                                                                .withLineAdded(burntBreadLore.getFirst())
                                                                )
                                                        )
                                                        .build()
                                        )
                                )
                        )
        );
        pack.addRecipe(Identifier.fromNamespaceAndPath(MOD_ID, "bread_furnace"),
                new JSmeltingRecipe(SmeltingTypes.SMELTING)
                        .cookingTime(100)
                        .experience(50)
                        .ingredient(
                                new JIngredient().entry("minecraft:wheat")
                        )
                        .result(
                                new JResult().id(Identifier.withDefaultNamespace("bread"))
                        )
        );
        pack.addRecipe(Identifier.fromNamespaceAndPath(MOD_ID, "bread_blast"),
                new JSmeltingRecipe(SmeltingTypes.BLASTING)
                        .cookingTime(80)
                        .experience(500)
                        .ingredient(
                                new JIngredient().entry("minecraft:wheat")
                        )
                        .result(
                                new JResult().id(Identifier.withDefaultNamespace("bread"))
                        )
        );
        pack.addRecipe(Identifier.fromNamespaceAndPath(MOD_ID, "bread_smoker"),
                new JSmeltingRecipe(SmeltingTypes.SMOKING)
                        .cookingTime(50)
                        .experience(5000)
                        .ingredient(
                                new JIngredient().entry("minecraft:wheat")
                        )
                        .result(
                                new JResult().id(Identifier.withDefaultNamespace("bread"))
                        )
        );
        pack.addRecipe(Identifier.fromNamespaceAndPath(MOD_ID, "bread_campfire"),
                new JSmeltingRecipe(SmeltingTypes.CAMPFIRE_COOKING)
                        .cookingTime(10)
                        .ingredient(
                                new JIngredient().entry("minecraft:wheat")
                        )
                        .result(
                                new JResult().id(Identifier.withDefaultNamespace("bread"))
                        )
        );
        pack.addRecipe(
                Identifier.fromNamespaceAndPath(MOD_ID, "bread_trims"),
                new JSmithingTrimRecipe()
                        .trimmableArmor()
                        .template(new JIngredient().tag("minecraft:trim_templates"))
                        .addition(new JIngredient().entry("minecraft:bread"))
        );

        // Custom Model Test
        pack.addModel(Identifier.fromNamespaceAndPath(MOD_ID, "block/test_model"),
                new JModel()
                        .element(
                                new JElement()
                                        .from(11, 0, 8)
                                        .to(13, 2, 10)
                                        .faces(JFaces.allSame(new JFace("missing").uv(0, 0, 2, 2)))
                        )
                        .element(
                                new JElement()
                                        .from(10, 0, 7)
                                        .to(12, 2, 9)
                                        .faces(JFaces.allSame(new JFace("missing").uv(0, 0, 2, 2)))
                        )
        );
        // Replace cobblestone block state
        pack.addBlockSate(Identifier.withDefaultNamespace("cobblestone"),
                new JState()
                        .add(new JVariant().model(new JBlockModel(Identifier.fromNamespaceAndPath(MOD_ID, "block/test_model"))))
        );
        pack.addTag(Identifier.withDefaultNamespace("block/mineable/pickaxe"), new JTag().add(Identifier.withDefaultNamespace("oak_log")));

        return pack;
    }
}
