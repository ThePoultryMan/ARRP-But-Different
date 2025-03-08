package io.github.thepoultryman.arrp_but_testing;

import io.github.thepoultryman.arrp_but_different.api.RuntimeResourcePack;
import io.github.thepoultryman.arrp_but_different.json.JLang;
import io.github.thepoultryman.arrp_but_different.json.model.JElement;
import io.github.thepoultryman.arrp_but_different.json.model.JFace;
import io.github.thepoultryman.arrp_but_different.json.model.JFaces;
import io.github.thepoultryman.arrp_but_different.json.model.JModel;
import io.github.thepoultryman.arrp_but_different.json.recipe.JIngredient;
import io.github.thepoultryman.arrp_but_different.json.recipe.JResult;
import io.github.thepoultryman.arrp_but_different.json.recipe.JSmithingRecipe;
import io.github.thepoultryman.arrp_but_different.json.recipe.crafting.JShapedRecipe;
import io.github.thepoultryman.arrp_but_different.json.recipe.crafting.JShapelessRecipe;
import io.github.thepoultryman.arrp_but_different.json.recipe.smelting.JSmeltingRecipe;
import io.github.thepoultryman.arrp_but_different.json.recipe.smelting.SmeltingTypes;
import io.github.thepoultryman.arrp_but_different.json.state.JBlockModel;
import io.github.thepoultryman.arrp_but_different.json.state.JState;
import io.github.thepoultryman.arrp_but_different.json.state.JVariant;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class ARRPButTestingCommon {
    public static final String MOD_ID = "arrp_but_testing";

    public static RuntimeResourcePack beforeUserEvent() {
        RuntimeResourcePack pack = RuntimeResourcePack.create(ResourceLocation.fromNamespaceAndPath(MOD_ID, "before_user"));
        pack.addLang(ResourceLocation.fromNamespaceAndPath(MOD_ID, "en_us"),
                new JLang().addBlockTranslation(ResourceLocation.withDefaultNamespace("torch"), "Torch but it's different but it's not so it's the same")
                        .addItemTranslation(Items.STICK, "It's still a stick")
        );
        pack.addBlockSate(ResourceLocation.withDefaultNamespace("acacia_door"),
                new JState().add(
                        new JVariant()
                                .condition("facing", "east")
                                .condition("half", "lower")
                                .condition("hinge", "left")
                                .condition("open", "false")
                                .model(new JBlockModel(ResourceLocation.withDefaultNamespace("block/spruce_door_bottom_left")))
                )
        );
        pack.addRecipe(ResourceLocation.fromNamespaceAndPath(MOD_ID, "pumpkin"),
                new JShapedRecipe()
                        .row(0, "PPP")
                        .row(1, "P P")
                        .row(2, "PPP")
                        .key("P", new JIngredient()
                                .entry("minecraft:pumpkin_pie")
                        )
                        .result(
                                new JResult()
                                        .id(ResourceLocation.withDefaultNamespace("pumpkin"))
                                        .count(3)
                        )
        );
        pack.addRecipe(
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "golden_sword"),
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
                                        .id(ResourceLocation.withDefaultNamespace("golden_sword"))
                                        .component("minecraft:damage", 3)
                                        .component("minecraft:rarity", "rare")
                        )
        );
        pack.addRecipe(ResourceLocation.fromNamespaceAndPath(MOD_ID, "bread_furnace"),
                new JSmeltingRecipe(SmeltingTypes.SMELTING)
                        .cookingTime(100)
                        .experience(50)
                        .ingredient(
                                new JIngredient().entry("minecraft:wheat")
                        )
                        .result(
                                new JResult().id(ResourceLocation.withDefaultNamespace("bread"))
                        )
        );
        pack.addRecipe(ResourceLocation.fromNamespaceAndPath(MOD_ID, "bread_blast"),
                new JSmeltingRecipe(SmeltingTypes.BLASTING)
                        .cookingTime(80)
                        .experience(500)
                        .ingredient(
                                new JIngredient().entry("minecraft:wheat")
                        )
                        .result(
                                new JResult().id(ResourceLocation.withDefaultNamespace("bread"))
                        )
        );
        pack.addRecipe(ResourceLocation.fromNamespaceAndPath(MOD_ID, "bread_smoker"),
                new JSmeltingRecipe(SmeltingTypes.SMOKING)
                        .cookingTime(50)
                        .experience(5000)
                        .ingredient(
                                new JIngredient().entry("minecraft:wheat")
                        )
                        .result(
                                new JResult().id(ResourceLocation.withDefaultNamespace("bread"))
                        )
        );
        pack.addRecipe(ResourceLocation.fromNamespaceAndPath(MOD_ID, "bread_campfire"),
                new JSmeltingRecipe(SmeltingTypes.CAMPFIRE_COOKING)
                        .cookingTime(10)
                        .ingredient(
                                new JIngredient().entry("minecraft:wheat")
                        )
                        .result(
                                new JResult().id(ResourceLocation.withDefaultNamespace("bread"))
                        )
        );
        pack.addRecipe(
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "bread_trims"),
                new JSmithingRecipe()
                        .trimmableArmor()
                        .template(new JIngredient().tag("minecraft:trim_templates"))
                        .addition(new JIngredient().entry("minecraft:bread"))
        );

        // Custom Model Test
        pack.addModel(ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/test_model"),
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
        pack.addBlockSate(ResourceLocation.withDefaultNamespace("cobblestone"),
                new JState()
                        .add(new JVariant().model(new JBlockModel(ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/test_model"))))
        );

        return pack;
    }
}
