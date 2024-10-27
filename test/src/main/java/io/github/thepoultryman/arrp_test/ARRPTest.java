package io.github.thepoultryman.arrp_test;

import com.mojang.logging.LogUtils;
import io.github.thepoultryman.arrp_neoforge.ARRPForNeoForge;
import io.github.thepoultryman.arrp_neoforge.api.RuntimeResourcePack;
import io.github.thepoultryman.arrp_neoforge.api.event.ARRPEvent;
import io.github.thepoultryman.arrp_neoforge.json.JLang;
import io.github.thepoultryman.arrp_neoforge.json.model.*;
import io.github.thepoultryman.arrp_neoforge.json.state.JBlockModel;
import io.github.thepoultryman.arrp_neoforge.json.state.JState;
import io.github.thepoultryman.arrp_neoforge.json.state.JVariant;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(ARRPTest.MOD_ID)
public class ARRPTest {
    public static final String MOD_ID = "arrp_test";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ARRPTest() {
        ARRPForNeoForge.ARRP_EVENT_BUS.addListener((ARRPEvent.BeforeUserEvent event) -> {
            LOGGER.info("Running BeforeUser event test");
            RuntimeResourcePack pack = RuntimeResourcePack.create(ResourceLocation.fromNamespaceAndPath(MOD_ID, "before_user"));
            pack.addLang(ResourceLocation.fromNamespaceAndPath(MOD_ID, "en_us"),
                    new JLang().addBlockTranslation(ResourceLocation.withDefaultNamespace("torch"), "Torch but it's different but it's not so it's the same")
            );
            pack.addBlockSate(ResourceLocation.withDefaultNamespace("wall_torch"),
                    new JState()
                            .add(
                                    new JVariant().put("facing", Direction.EAST,
                                            new JBlockModel(ResourceLocation.withDefaultNamespace("block/wall_torch"))
                                    )
                            )
                            .add(
                                    new JVariant().put("facing", Direction.SOUTH,
                                            new JBlockModel(ResourceLocation.withDefaultNamespace("block/wall_torch")).y(90)
                                    )
                            )
                            .add(
                                    new JVariant().put("facing", Direction.WEST,
                                            new JBlockModel(ResourceLocation.withDefaultNamespace("block/wall_torch")).y(180)
                                    )
                            )
                            .add(
                                    new JVariant().put("facing", Direction.NORTH,
                                            new JBlockModel(ResourceLocation.withDefaultNamespace("block/wall_torch")).y(270)
                                    )
                            )
            );
            pack.addModel(ResourceLocation.withDefaultNamespace("block/torch"),
                    new JModel()
                            .noAmbientOcclusion()
                            .textures(
                                    new JTextures().add("torch", "minecraft:block/torch")
                                            .particle("#torch")
                            )
                            .element(
                                    new JElement()
                                            .from(7, 0, 7)
                                            .to(9, 10, 9)
                                            .noShade()
                                            .faces(
                                                    new JFaces()
                                                            .down(
                                                                    new JFace("torch")
                                                                            .uv(7, 13, 9, 15)
                                                            )
                                                            .up(
                                                                    new JFace("torch")
                                                                            .uv(7, 6, 9, 8)
                                                            )
                                            ),
                                    new JElement()
                                            .from(7, 0, 0)
                                            .to(9, 16, 16)
                                            .noShade()
                                            .faces(
                                                    new JFaces()
                                                            .west(
                                                                    new JFace("torch")
                                                                            .uv(0, 0, 16, 16)
                                                            )
                                                            .east(
                                                                    new JFace("torch")
                                                                            .uv(0, 0, 16, 16)
                                                            )
                                            ),
                                    new JElement()
                                            .from(0, 0, 7)
                                            .to(16, 16, 9)
                                            .noShade()
                                            .faces(
                                                    new JFaces()
                                                            .north(
                                                                    new JFace("torch")
                                                                            .uv(0, 0, 16, 16)
                                                            )
                                                            .south(
                                                                    new JFace("torch")
                                                                            .uv(0, 0, 16, 16)
                                                            )
                                            ),
                                    new JElement()
                                            .from(6, 0, 6)
                                            .to(10, 2, 10)
                                            .faces(
                                                    new JFaces()
                                                            .north(
                                                                    new JFace("missing")
                                                                            .uv(0, 0, 4, 2)
                                                            )
                                                            .east(
                                                                    new JFace("missing")
                                                                            .uv(0, 0, 4, 2)
                                                            )
                                                            .south(
                                                                    new JFace("missing")
                                                                            .uv(0, 0, 4, 2)
                                                            )
                                                            .west(
                                                                    new JFace("missing")
                                                                            .uv(0, 0, 4, 2)
                                                            )
                                                            .up(
                                                                    new JFace("missing")
                                                                            .uv(0, 0, 4, 4)
                                                            )
                                            )
                            )
            );
            event.addPack(pack);
        });
    }
}
