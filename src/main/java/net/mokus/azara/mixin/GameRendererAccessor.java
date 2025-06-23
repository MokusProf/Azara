package net.mokus.azara.mixin;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

//THANKS TO eyalego and Yorick-06 for the help for this code <3

@Mixin(GameRenderer.class)
public interface GameRendererAccessor {
    @Invoker("loadPostProcessor")
    void invokeLoadPostProcessor(Identifier id);

    @Invoker("disablePostProcessor")
    void invokeDisablePostProcessor();
}