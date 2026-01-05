package io.github.thepoultryman.arrp_but_different.mixins;

//? if >= 1.21.11 {
import net.minecraft.client.renderer.rendertype.RenderType;
//? } else
//import net.minecraft.client.renderer.RenderStateShard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

//? if >= 1.21.11 {
@Mixin(RenderType.class)
//? } else
//@Mixin(RenderStateShard.class)
public interface RenderTypeAccessor {
    @Accessor(value = "name")
    String getName();
}
