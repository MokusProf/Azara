package net.mokus.azara.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class BloomEffect extends StatusEffect{
    public BloomEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.hasStatusEffect(StatusEffects.SPEED)) {
            entity.removeStatusEffect(StatusEffects.SPEED);
        }
        if (entity instanceof PlayerEntity) {
            ((PlayerEntity)entity).getHungerManager().add(amplifier + 4, 1.0F);
        }
        if (entity.getHealth() < entity.getMaxHealth()) {
                entity.heal(2.0F);
            }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i;
            i = 50 >> amplifier;
            if (i > 0) {
                return duration % i == 0;
            } else {
                return true;
            }
    }
}
