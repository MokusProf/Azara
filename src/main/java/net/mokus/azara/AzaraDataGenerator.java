package net.mokus.azara;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.mokus.azara.datagen.ModItemTagProvider;
import net.mokus.azara.datagen.ModModelProvider;
import net.mokus.azara.datagen.ModWorldGenerator;
import net.mokus.azara.world.ModConfiguredFeatures;
import net.mokus.azara.world.ModPlacedFeatures;
import net.mokus.azara.world.gen.ModFlowerGeneration;
import net.mokus.azara.world.gen.ModWorldGeneration;

public class AzaraDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModWorldGenerator::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModItemTagProvider::new);

	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder){
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::boostrap);
	}
}
