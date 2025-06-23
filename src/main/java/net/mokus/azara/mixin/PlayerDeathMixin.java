package net.mokus.azara.mixin;

import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import net.mokus.azara.block.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(ServerPlayerEntity.class)
public abstract class PlayerDeathMixin {

    private boolean wasDeadLastTick = false;

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;

        UUID targetUUID = UUID.fromString("c1115be4-d7e8-4979-b1bd-1a6820c736da");
        boolean isNowDead = player.isDead();

        if (!wasDeadLastTick && isNowDead && player.getUuid().equals(targetUUID)) {
            World world = player.getWorld();
            if (!world.isClient) {
                ItemEntity apple = new ItemEntity(
                        world,
                        player.getX(), player.getY(), player.getZ(),
                        new ItemStack(ModBlocks.VIR_FLOWER)
                );
                world.spawnEntity(apple);
            }
        }

        wasDeadLastTick = isNowDead;
    }
}
