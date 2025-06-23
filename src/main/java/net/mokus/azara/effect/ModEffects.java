package net.mokus.azara.effect;


import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.mokus.azara.Azara;

public class ModEffects {
    //THANKS TO eyalego and Yorick-06 for the help for the shader code <3
    public static final StatusEffect ABYSSEFFECT = Registry.register(Registries.STATUS_EFFECT, Identifier.of(Azara.MOD_ID, "abyss"), new AbyssEffect());

    public static final StatusEffect BLOOMEFFECT = Registry.register(Registries.STATUS_EFFECT, Identifier.of(Azara.MOD_ID,"bloom"), new BloomEffect(StatusEffectCategory.BENEFICIAL,0x00FF00)
            .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,"46b03222-a746-3518-9d51-45cce3668273",0.2f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));

    public static final StatusEffect RAGEEFFECT = Registry.register(Registries.STATUS_EFFECT, Identifier.of(Azara.MOD_ID,"rage"), new RageEffect(StatusEffectCategory.BENEFICIAL,0xA70C0C)
            .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE,"457abfe7-6919-305f-8681-9aa9dd824a2e",0.30f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect){
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Azara.MOD_ID, name), statusEffect);
    }

    public static void registerEffects(){
    }
}
