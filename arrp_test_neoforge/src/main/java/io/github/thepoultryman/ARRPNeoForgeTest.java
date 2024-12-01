package io.github.thepoultryman;

import com.google.gson.JsonObject;
import io.github.thepoultryman.arrp_neoforge.api.RuntimeResourcePack;
import io.github.thepoultryman.arrp_neoforge.json.JLang;
import io.github.thepoultryman.arrp_neoforge.json.recipe.JIngredient;
import io.github.thepoultryman.arrp_neoforge.json.recipe.JResult;
import io.github.thepoultryman.arrp_neoforge.json.recipe.crafting.JShapedRecipe;
import io.github.thepoultryman.arrp_neoforge.json.recipe.crafting.JShapelessRecipe;
import io.github.thepoultryman.arrp_neoforge.json.state.JBlockModel;
import io.github.thepoultryman.arrp_neoforge.json.state.JState;
import io.github.thepoultryman.arrp_neoforge.json.state.JVariant;
import io.github.thepoultryman.arrp_neoforge.neoforge.ARRPForNeoForge;
import io.github.thepoultryman.arrp_neoforge.neoforge.ARRPNeoForgeEvent;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.common.Mod;

import java.util.Arrays;

@Mod(ARRPNeoForgeTest.MOD_ID)
public class ARRPNeoForgeTest {
    static final String MOD_ID = "arrp_neoforge_test";

    public ARRPNeoForgeTest() {
        ARRPForNeoForge.ARRP_EVENT_BUS.addListener((ARRPNeoForgeEvent.BeforeUserNeoForgeEvent event) -> {
            RuntimeResourcePack pack = RuntimeResourcePack.create(ResourceLocation.fromNamespaceAndPath(MOD_ID, "before_user"));
            pack.addLang(ResourceLocation.fromNamespaceAndPath(MOD_ID, "en_us"),
                    new JLang().addBlockTranslation(ResourceLocation.withDefaultNamespace("torch"), "Torch but it's different but it's not so it's the same")
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
            event.addPack(pack);
        });
    }
}