package net.mokus.azara.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.mokus.azara.Azara;
import net.mokus.azara.block.ModBlocks;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> VIR_FLOWER_KEY = registerKey("vir_flower");
    public static final RegistryKey<ConfiguredFeature<?, ?>> VIR_PETALS_KEY = registerKey("vir_petals");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ABYSS_FLOWER_KEY = registerKey("abyss_flower");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ABYSS_PETALS_KEY = registerKey("abyss_petals");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SRPING_FLOWER_KEY = registerKey("spring_flower");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context){

        register(context, VIR_FLOWER_KEY, Feature.FLOWER, new RandomPatchFeatureConfig(32, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.VIR_FLOWER)))));
        register(context, VIR_PETALS_KEY, Feature.FLOWER, new RandomPatchFeatureConfig(32, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.VIR_PETALS)))));
       register(context, ABYSS_FLOWER_KEY, Feature.FLOWER, new RandomPatchFeatureConfig(32, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.ABYSS_FLOWER)))));
        register(context, ABYSS_PETALS_KEY, Feature.FLOWER, new RandomPatchFeatureConfig(32, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.ABYSS_PETALS)))));
        register(context, SRPING_FLOWER_KEY, Feature.FLOWER, new RandomPatchFeatureConfig(32, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.SPRING_FLOWER)))));

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Azara.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
