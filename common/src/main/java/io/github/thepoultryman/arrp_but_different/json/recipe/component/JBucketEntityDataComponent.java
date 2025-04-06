package io.github.thepoultryman.arrp_but_different.json.recipe.component;

import com.google.gson.annotations.SerializedName;
import net.minecraft.resources.ResourceLocation;

public class JBucketEntityDataComponent extends AbstractJComponent {
    private final ResourceLocation id;
    @SerializedName("HuntingCooldown")
    private Integer huntingCooldown = 0;
    @SerializedName("BucketVariantTag")
    private Integer bucketVariantTag = 0;

    public JBucketEntityDataComponent(ResourceLocation id) {
        this.id = id;
    }

    public JBucketEntityDataComponent huntingCooldown(int huntingCooldown) {
        this.huntingCooldown = huntingCooldown;
        return this;
    }

    public JBucketEntityDataComponent bucketVariantTag(int bucketVariantTag) {
        this.bucketVariantTag = bucketVariantTag;
        return this;
    }
}
