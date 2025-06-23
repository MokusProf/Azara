package net.mokus.azara.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.mokus.azara.item.DamageTrackingWeapon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//THANKS TO eyalego and Yorick-06 for the help for this code <3

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixinServer {
    //ALL OF THIS SHIT JUST TO CALCULATE THE PROPER DAMAGE I HAVE GIVEN TO A FUCKING ENTITY
    @Unique
    private float azara$preDamageHealth;

    @Inject(method = "damage", at = @At("HEAD"))
    private void beforeDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        this.azara$preDamageHealth = ((LivingEntity) (Object) this).getHealth();
    }

    @Inject(method = "damage", at = @At("TAIL"))
    private void afterDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) return;

        LivingEntity self = (LivingEntity)(Object) this;
        World world = self.getWorld();

        if (world.isClient()) return;

        if (!(source.getAttacker() instanceof ServerPlayerEntity player)) return;

        ItemStack stack = player.getMainHandStack();
        if (!(stack.getItem() instanceof DamageTrackingWeapon)) return;

        float actualDamage = azara$preDamageHealth - self.getHealth();
        if (actualDamage <= 0) return;

        NbtCompound nbt = stack.getOrCreateNbt();
        int current = nbt.getInt("DamageDealt");
        nbt.putInt("DamageDealt", current + MathHelper.floor(actualDamage));

    }
}

