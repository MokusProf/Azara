package net.mokus.azara.item.useonce;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.mokus.azara.Azara;
import net.minecraft.sound.SoundEvents;
import net.mokus.azara.effect.ModEffects;
import net.mokus.azara.item.DamageTrackingWeapon;

import java.util.List;

public class AbyssCallSword extends SwordItem implements DamageTrackingWeapon {
    private static final String DAMAGE_KEY = "DamageDealt";
    public static final int DAMAGE_THRESHOLD = 20; // 10 hearts

    public AbyssCallSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);

    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            HitResult hitResult = user.raycast(5.0D, 0.0F, false);
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                return TypedActionResult.pass(stack);
            }
            int damageDealt = getDamageDealt(stack);

            if (damageDealt >= DAMAGE_THRESHOLD) {
                int radius = 5;
                List<LivingEntity> entities = world.getEntitiesByClass(
                        LivingEntity.class,
                        user.getBoundingBox().expand(radius),
                        (entity) -> entity != user
                );

                for (LivingEntity entity : entities) {
                    entity.addStatusEffect(new StatusEffectInstance(ModEffects.ABYSSEFFECT, 200, 0));
                }
                user.addStatusEffect(new StatusEffectInstance(ModEffects.BLOOMEFFECT,400,0));
                setDamageDealt(stack, 0);

                // Spawn particle circle around player, THIS IS GETTING FUCKING REDICULOUS THERE HAS TO BE A BETTER WAY TO FUCKING DO THIS BUT I DON'T KNOW HOW
                int particleCount = 40;
                double y = user.getY() + 0.5;

                for (double currentRadius = 1.0; currentRadius <= radius; currentRadius += 0.5) {
                    double angleStep = 2 * Math.PI / particleCount;

                    for (int i = 0; i < particleCount; i++) {
                        double angle = i * angleStep;
                        double xOffset = Math.cos(angle) * currentRadius;
                        double zOffset = Math.sin(angle) * currentRadius;
                        double x = user.getX() + xOffset;
                        double z = user.getZ() + zOffset;

                        ((ServerWorld) world).spawnParticles(
                                ParticleTypes.WITCH,
                                x, y, z,
                                1,
                                0, 0, 0,
                                1
                        );
                    }
                    world.playSound(
                            null,
                            user.getBlockPos(),
                            SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON,
                            user.getSoundCategory(),
                            1.0f, 1.0f
                    );
                    updateModel(stack);
                }
            } else {

            }
        }

        return TypedActionResult.success(stack);
    }

    private void updateModel(ItemStack stack) {
        int damage = getDamageDealt(stack);
        float progress = Math.min(1.0f, damage / (float) DAMAGE_THRESHOLD);
        int modelStage;

        if (progress >= 1.0f) {
            modelStage = 4;
        } else if (progress >= 0.75f) {
            modelStage = 3;
        } else if (progress >= 0.5f) {
            modelStage = 2;
        } else if (progress >= 0.25f) {
            modelStage = 1;
        } else {
            modelStage = 0;
        }

        stack.getOrCreateNbt().putInt("CustomModelData", modelStage);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        updateModel(stack);
        return true;
    }

    private int getDamageDealt(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        return nbt.getInt(DAMAGE_KEY);
    }

    private void setDamageDealt(ItemStack stack, int amount) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putInt(DAMAGE_KEY, amount);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        int damage = getDamageDealt(stack);
        float progress = Math.min(1.0f, damage / (float) DAMAGE_THRESHOLD);
        return Math.round(progress * 13);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        int damage = getDamageDealt(stack);
        float progress = Math.min(1.0f, damage / (float) DAMAGE_THRESHOLD);
        int startRed = 0;
        int startGreen = 255;
        int startBlue = 255;

        int endRed = 75;
        int endGreen = 0;
        int endBlue = 130;

        int red = (int) (startRed + progress * (endRed - startRed));
        int green = (int) (startGreen + progress * (endGreen - startGreen));
        int blue = (int) (startBlue + progress * (endBlue - startBlue));

        return (red << 16) | (green << 8) | blue;
    }
}

