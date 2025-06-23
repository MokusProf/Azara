package net.mokus.azara;

import net.fabricmc.api.ModInitializer;

import net.mokus.azara.effect.ModEffects;
import net.mokus.azara.entity.ModBlockEntities;
import net.mokus.azara.item.Registry.ModItemGroups;
import net.mokus.azara.item.Registry.ModItems;
import net.mokus.azara.block.ModBlocks;
import net.mokus.azara.potion.ModPotions;
import net.mokus.azara.sound.ModSounds;
import net.mokus.azara.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Azara implements ModInitializer {
	public static final String MOD_ID = "azara";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModWorldGeneration.generateModWorldGeneration();
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.register();

		ModPotions.registerPotions();
		ModEffects.registerEffects();
		ModPotions.registerPotionsRecipes();

		ModSounds.registerSounds();


	}

}