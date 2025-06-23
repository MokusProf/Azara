package net.mokus.azara.item.useonce;

import net.minecraft.client.item.TooltipContext;
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
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.mokus.azara.Azara;
import net.mokus.azara.effect.ModEffects;
import net.mokus.azara.item.DamageTrackingWeapon;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BloomSword extends SwordItem implements DamageTrackingWeapon {
    private static final String DAMAGE_KEY = "DamageDealt";
    public static final int DAMAGE_THRESHOLD = 20; // 10 hearts

    public BloomSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
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
                user.addStatusEffect(new StatusEffectInstance(ModEffects.BLOOMEFFECT,100,0));
                setDamageDealt(stack, 0);
                return TypedActionResult.success(stack);
                }
            } else {

            }
        return TypedActionResult.pass(stack);
        }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
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

        int endRed = 50;
        int endGreen = 205;
        int endBlue = 50;

        int red = (int) (startRed + progress * (endRed - startRed));
        int green = (int) (startGreen + progress * (endGreen - startGreen));
        int blue = (int) (startBlue + progress * (endBlue - startBlue));

        return (red << 16) | (green << 8) | blue;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.azara.dtw.desc"));
        tooltip.add(Text.translatable("item.azara.bloom_sword.desc"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}

