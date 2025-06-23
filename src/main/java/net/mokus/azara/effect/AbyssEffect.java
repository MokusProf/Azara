package net.mokus.azara.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class AbyssEffect extends StatusEffect {
    public static final int COLOR = 0x36013f;
    public AbyssEffect() {
        super(StatusEffectCategory.HARMFUL, COLOR);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity) {
            ((PlayerEntity)entity).getHungerManager().add(amplifier - 1, -1.0F);
        }
        if (entity.getHealth() > 10.0F) {
            entity.damage(entity.getDamageSources().magic(), 1.0F);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i;
        i = 25 >> amplifier;
        if (i > 0) {
            return duration % i == 0;
        } else {
            return true;
        }
    }
}
