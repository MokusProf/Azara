package net.mokus.azara.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class RageEffect extends StatusEffect{
    public RageEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.hasStatusEffect(StatusEffects.STRENGTH)) {
            entity.removeStatusEffect(StatusEffects.STRENGTH);
            entity.addStatusEffect(new StatusEffectInstance(
                    ModEffects.RAGEEFFECT,
                    100,
                    1,
                    false,
                    false,
                    true

            ));
        }
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 20 == 0;
    }
}
