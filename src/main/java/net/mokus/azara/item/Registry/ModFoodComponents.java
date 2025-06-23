package net.mokus.azara.item.Registry;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.mokus.azara.Azara;
import net.mokus.azara.effect.ModEffects;

public class ModFoodComponents {
    public static final FoodComponent ABYSS_DUST = new FoodComponent.Builder().hunger(0).saturationModifier(0)
                .statusEffect(new StatusEffectInstance(ModEffects.ABYSSEFFECT,400),1f)
                .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,400,1),1.0f)
                .statusEffect(new StatusEffectInstance(StatusEffects.SPEED,400,1),1.0f)
                .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA,400,0),0.60f)
                .alwaysEdible()
                .build();

    public static final FoodComponent BLOOM_DUST = new FoodComponent.Builder().hunger(2).saturationModifier(2)
            .statusEffect(new StatusEffectInstance(ModEffects.BLOOMEFFECT,100,0),1.0f)
            .build();
}
