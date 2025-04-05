package io.github.thepoultryman.arrp_but_different.json.recipe.banner;

import com.google.gson.annotations.SerializedName;
import net.minecraft.resources.ResourceLocation;

public class JBannerPatternObject {
    @SerializedName("asset_id")
    private ResourceLocation assetId;
    @SerializedName("translation_key")
    private String translationKey;

    public JBannerPatternObject assetId(ResourceLocation assetId) {
        this.assetId = assetId;
        return this;
    }

    public JBannerPatternObject translationKey(String translationKey) {
        this.translationKey = translationKey;
        return this;
    }
}
