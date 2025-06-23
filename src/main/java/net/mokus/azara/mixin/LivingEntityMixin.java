package net.mokus.azara.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.Identifier;
import net.mokus.azara.Azara;
import net.mokus.azara.effect.ModEffects;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//THANKS TO eyalego and Yorick-06 for the help for this code <3

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow protected abstract void playBlockFallSound();

    @Shadow public abstract void remove(Entity.RemovalReason reason);

    @Inject(method = "onStatusEffectApplied", at = @At("HEAD"))
    protected void applyAbyssShader(StatusEffectInstance effect, @Nullable Entity source, CallbackInfo info){
        if ((Object) this instanceof ClientPlayerEntity && effect.getEffectType() == ModEffects.ABYSSEFFECT){
            ((GameRendererAccessor) MinecraftClient.getInstance().gameRenderer).invokeLoadPostProcessor(
                    new Identifier("shaders/post/purple.json"));
        }
    }

    @Inject(method = "removeStatusEffectInternal", at = @At("HEAD"))
    protected void removeAbyssShader(StatusEffect type, CallbackInfoReturnable<StatusEffectInstance> info) {
        if ((Object) this instanceof ClientPlayerEntity && type == ModEffects.ABYSSEFFECT) {
            ((GameRendererAccessor) MinecraftClient.getInstance().gameRenderer).invokeDisablePostProcessor();
        }
    }
    @Mixin(ClientPlayerEntity.class)
    public abstract static class ClientPlayerEntityMixin {

        @Unique
        private boolean hadAbyssEffectLastTick = false;

        @Inject(method = "tick", at = @At("HEAD"))
        private void onTick(CallbackInfo ci) {
            ClientPlayerEntity player = (ClientPlayerEntity)(Object)this;

            boolean hasEffect = player.hasStatusEffect(ModEffects.ABYSSEFFECT);
            boolean isDead = player.isDead();

            if (isDead && hadAbyssEffectLastTick) {
                ((GameRendererAccessor) MinecraftClient.getInstance().gameRenderer).invokeDisablePostProcessor();
            }
            hadAbyssEffectLastTick = hasEffect && !isDead;
        }
    }
}

