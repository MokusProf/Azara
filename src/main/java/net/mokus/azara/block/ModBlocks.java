package net.mokus.azara.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mokus.azara.Azara;
import net.mokus.azara.effect.ModEffects;
import net.mokus.azara.entity.BloomDisplayBlock;

public class ModBlocks {
// Blocks here UwU
    public static final Block ABYSS_LANTERN = registerBlock("abyss_lantern",
        new Block(FabricBlockSettings.copyOf(Blocks.SEA_LANTERN).mapColor(MapColor.MAGENTA)));

    public static final Block REDSTONE_LAMP_ON = registerBlock("redstone_lamp_on",
            new Block(FabricBlockSettings.copyOf(Blocks.REDSTONE_LAMP).luminance(15)));

    public static final Block AQUA_LANTERN = registerBlock("aqua_lantern",
            new Block(FabricBlockSettings.copyOf(Blocks.SEA_LANTERN)));
    public static final Block BLUE_LANTERN = registerBlock("blue_lantern",
            new Block(FabricBlockSettings.copyOf(Blocks.SEA_LANTERN)));
    public static final Block GREEN_LANTERN = registerBlock("green_lantern",
            new Block(FabricBlockSettings.copyOf(Blocks.SEA_LANTERN)));
    public static final Block RED_LANTERN = registerBlock("red_lantern",
            new Block(FabricBlockSettings.copyOf(Blocks.SEA_LANTERN)));
    public static final Block YELLOW_LANTERN = registerBlock("yellow_lantern",
            new Block(FabricBlockSettings.copyOf(Blocks.SEA_LANTERN)));

    public static final Block COMPACTED_FLOWERS = registerBlock("compacted_flowers",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES)));

    public static final Block SPRING_BLOCK = registerBlock("spring_block",
            new SpringBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));

    public static final Block BLOOM_BLOCK = registerBlock("bloom_block",
            new GrowthBoosterBlock(FabricBlockSettings.copyOf(Blocks.STONE)));

    public static final Block ADV_BLOOM_BLOCK = registerBlock("adv_bloom_block",
            new BloomDisplayBlock(FabricBlockSettings.copyOf(Blocks.BEACON)));

    //Abyss corrupted blocks here

    public static final Block ABYSS_PLANKS = registerBlock("abyss_planks",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));

   public static final Block ABYSS_LOG = registerBlock("abyss_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));




// Flowers here
    public static final Block ABYSS_FLOWER = registerBlock("abyss_flower",
            new FlowerBlock(ModEffects.ABYSSEFFECT,10,  FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque().noCollision()));

    public static final Block ABYSS_PETALS = registerBlock("abyss_petals",
            new FlowerbedBlock(FabricBlockSettings.copyOf(Blocks.PINK_PETALS).nonOpaque().noCollision()));

    public static final Block VIR_PETALS = registerBlock("vir_petals",
            new FlowerbedBlock(FabricBlockSettings.copyOf(Blocks.PINK_PETALS).nonOpaque().noCollision()));

    public static final Block VIR_FLOWER = registerBlock("vir_flower",
            new FlowerBlock(ModEffects.BLOOMEFFECT, 10, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque().noCollision()));

    public static final Block SPRING_FLOWER = registerBlock("spring_flower",
            new SpringFlower(StatusEffects.JUMP_BOOST,10, FabricBlockSettings.copyOf(Blocks.POPPY).nonOpaque().noCollision()));

// Potted Flowers here
    public static final Block POTTED_ABYSS_FLOWER = Registry.register(Registries.BLOCK, new Identifier(Azara.MOD_ID, "potted_abyss_flower"),
            new FlowerPotBlock(ABYSS_FLOWER, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));

    public static final Block POTTED_VIR_FLOWER = Registry.register(Registries.BLOCK, new Identifier(Azara.MOD_ID, "potted_vir_flower"),
            new FlowerPotBlock(VIR_FLOWER, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));

    public static final Block POTTED_SPRING_FLOWER = Registry.register(Registries.BLOCK, new Identifier(Azara.MOD_ID, "potted_spring_flower"),
            new FlowerPotBlock(SPRING_FLOWER, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY).nonOpaque()));


    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Azara.MOD_ID, name), block);
    }


    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(Azara.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }


    public static void registerModBlocks(){
        Azara.LOGGER.info("Registering ModBlocks for"+ Azara.MOD_ID);
    }
}
