package net.mokus.azara.potion;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.mokus.azara.Azara;
import net.mokus.azara.block.ModBlocks;
import net.mokus.azara.effect.ModEffects;
import net.mokus.azara.item.Registry.ModItems;

public class ModPotions {

    public static final Potion BLOOM_POTION = Registry.register(
            Registries.POTION,
            new Identifier("azara", "bloom_potion"),
            new Potion(new StatusEffectInstance(ModEffects.BLOOMEFFECT, 200))
    );
    public static final Potion ABYSS_POTION = Registry.register(
            Registries.POTION,
            new Identifier("azara", "abyss_potion"),
            new Potion(new StatusEffectInstance(ModEffects.ABYSSEFFECT, 200))
    );

    public static final Potion LEVITATION = Registry.register(
            Registries.POTION,
            new Identifier("azara", "levitation"),
            new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 200))
    );
    public static final Potion STRONG_LEVITATION = Registry.register(
            Registries.POTION,
            new Identifier("azara", "strong_levitation"),
            new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 100,1))
    );
    public static final Potion LONG_LEVITATION = Registry.register(
            Registries.POTION,
            new Identifier("azara", "long_levitation"),
            new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 400,0))
    );

    private static RegistryEntry<Potion> registerPotion(String name, Potion potion){
        return Registry.registerReference(Registries.POTION, Identifier.of(Azara.MOD_ID, name),potion);
    }

    public static void registerPotions(){

    }

    public static void registerPotionsRecipes(){
        BrewingRecipeRegistry.registerPotionRecipe(
                Potions.REGENERATION,
                ModItems.BLOOM_DUST,
                ModPotions.BLOOM_POTION
        );
        BrewingRecipeRegistry.registerPotionRecipe(
                Potions.POISON,
                ModItems.ABYSS_DUST,
                ModPotions.ABYSS_POTION
        );
        BrewingRecipeRegistry.registerPotionRecipe(
                Potions.AWKWARD,
                Item.fromBlock(ModBlocks.SPRING_FLOWER),
                ModPotions.LEVITATION
        );
        BrewingRecipeRegistry.registerPotionRecipe(
                ModPotions.LEVITATION,
                Items.REDSTONE,
                ModPotions.LONG_LEVITATION
        );
        BrewingRecipeRegistry.registerPotionRecipe(
                ModPotions.LEVITATION,
                Items.GLOWSTONE_DUST,
                ModPotions.STRONG_LEVITATION
        );
    }
}
