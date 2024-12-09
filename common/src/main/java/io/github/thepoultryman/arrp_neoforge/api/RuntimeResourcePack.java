package io.github.thepoultryman.arrp_neoforge.api;

import io.github.thepoultryman.arrp_neoforge.impl.RuntimeResourcePackImpl;
import io.github.thepoultryman.arrp_neoforge.json.JLang;
import io.github.thepoultryman.arrp_neoforge.json.JTag;
import io.github.thepoultryman.arrp_neoforge.json.animation.JAnimation;
import io.github.thepoultryman.arrp_neoforge.json.loot.JLootTable;
import io.github.thepoultryman.arrp_neoforge.json.model.JModel;
import io.github.thepoultryman.arrp_neoforge.json.recipe.AbstractJRecipe;
import io.github.thepoultryman.arrp_neoforge.json.state.JState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.function.BiFunction;
import java.util.function.IntUnaryOperator;
import java.util.zip.ZipInputStream;

public interface RuntimeResourcePack extends PackResources {
    /**
     * Creates a runtime resource pack.
     * @param resourceLocation The ID for the resource pack.
     * @return {@link RuntimeResourcePack}
     */
    static RuntimeResourcePack create(ResourceLocation resourceLocation) {
        return new RuntimeResourcePackImpl(resourceLocation);
    }

    /**
     * Clones and recolors the texture given by the input stream, and puts the
     * creates the image at the given location.
     * @param resourceLocation The location where the new image will be put.
     * @param inputStream The input stream of the original texture
     * @param pixel The pixel recolorer.
     */
    void addRecoloredImage(ResourceLocation resourceLocation, InputStream inputStream, IntUnaryOperator pixel);

    /**
     * Adds a language file for the given language.
     * <br />
     * Calling this method multiple times for the same language will override
     * previous calls. Use {@link RuntimeResourcePack#mergeLang} if you need to call the method
     * multiple times.
     * @param resourceLocation The namespace of your mod and the path of the language.
     * @param lang The translation information.
     * @return TODO: Add return information.
     */
    byte[] addLang(ResourceLocation resourceLocation, JLang lang);

    /**
     * Adds a language file for the given language.
     * <br />
     * Calling this method multiple times for the same language will merge the
     * data into one file.
     * @param resourceLocation The namespace of your mod and the path of the language.
     * @param lang The translation information.
     */
    void mergeLang(ResourceLocation resourceLocation, JLang lang);

    /**
     * Adds a loot table.
     * @param resourceLocation The path to the loot table.
     * @param lootTable The loot table data.
     * @return TODO: Add return information.
     */
    byte[] addLootTable(ResourceLocation resourceLocation, JLootTable lootTable);

    /**
     * Adds a resource that is lazily evaluated.
     * @param packType Whether the resource is an asset or data.
     * @param path The path to the resource.
     * @param data A function that is evaluated at a later time.
     */
    void addLazyResource(PackType packType, ResourceLocation path, BiFunction<RuntimeResourcePack, ResourceLocation, byte[]> data);

    /**
     * Adds a raw resource.
     * @param packType Whether the resource is an asset or data.
     * @param path The path to the resource
     * @param data The resource data
     * @return TODO: Add return information.
     */
    byte[] addResource(PackType packType, ResourceLocation path, byte[] data);

    /**
     * Adds a raw resource to the pack's root path.
     * @param path The path to the resource.
     * @param data The resource data
     * @return TODO: Add return information
     */
    byte[] addRootResource(String path, byte[] data);

    /**
     * Adds an asset, which should be clientside.
     * @param path The path to the resource
     * @param data The asset data
     * @return TODO: Add return information.
     */
    byte[] addAsset(ResourceLocation path, byte[] data);

    /**
     * Adds a data resource, which should be serverside.
     * @param path The path to the resource.
     * @param data The data's data
     * @return TODO: Add return information.
     */
    byte[] addData(ResourceLocation path, byte[] data);

    /**
     * Adds a model. Items should go into item/... and blocks should go in block/...
     * <br />
     * ".json" is automatically added to the path.
     * @param path The path to the model.
     * @param model The model data.
     * @return TODO: Add return information.
     */
    byte[] addModel(ResourceLocation path, JModel model);

    /**
     * Adds a blockstate file.
     * @param path The path to the blockstate file.
     * @param state The blockstate data.
     * @return TODO: Add return information.
     */
    byte[] addBlockSate(ResourceLocation path, JState state);

    /**
     * Adds a texture file.
     * <br />
     * ".png" is automatically added to the path.
     * @param path The path to the texture.
     * @param image The image
     * @return TODO: Add return information.
     */
    byte[] addTexture(ResourceLocation path, BufferedImage image);

    /**
     * Adds an animation metadata file for a texture.
     * <br />
     * ".png.mcmeta" is already added to the path.
     * @param path The path to the texture.
     * @param animation The animation data.
     * @return TODO: Add return information.
     */
    byte[] addAnimation(ResourceLocation path, JAnimation animation);

    /**
     * Adds a tag.
     * <br />
     * ".json" is automatically added to the path.
     * @param path The path to the tag.
     * @param tag The tag data.
     * @return TODO: Add return information.
     */
    byte[] addTag(ResourceLocation path, JTag tag);

    /**
     * Adds a recipe.
     * <br />
     * ".json" is automatically added to the path.
     * @param path The location of the recipe that also represents its directory.
     * @param recipe The recipe data.
     * @return TODO: Add return information.
     */
    byte[] addRecipe(ResourceLocation path, AbstractJRecipe recipe);

    void load(Path path) throws IOException;

    void load(ZipInputStream zipStream) throws IOException;
}
