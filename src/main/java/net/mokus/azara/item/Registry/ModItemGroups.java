package net.mokus.azara.item.Registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.mokus.azara.Azara;
import net.mokus.azara.block.ModBlocks;
import org.spongepowered.asm.mixin.injection.selectors.ElementNode;

public class ModItemGroups {
    public static final ItemGroup ABBYS_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(Azara.MOD_ID, "abyss_petals"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.abyss"))
                    .icon(() -> new ItemStack(ModBlocks.ABYSS_PETALS)).entries((displayContext, entries) -> {

                        entries.add(ModItems.ABYSSAL_SWORD);
                       // entries.add(ModItems.PARTICLE_STICK); //Test item for aaa particles
                        entries.add(ModItems.BLOOM_SWORD);
                        entries.add(ModItems.RAGE_SWORD);
                        entries.add(ModItems.GREATER_RAGE_SWORD);
                        entries.add(ModItems.GREATER_BLOOM_SWORD);
                        entries.add(ModItems.ABYSS_NETHERITE);

                        //Armors

                        entries.add(ModItems.ABYSS_NETHERITE_HELMET);
                        entries.add(ModItems.ABYSS_NETHERITE_CHESTPLATE);
                        entries.add(ModItems.ABYSS_NETHERITE_LEGGINGS);
                        entries.add(ModItems.ABYSS_NETHERITE_BOOTS);

                        //Consumables
                        entries.add(ModItems.ABYSS_DUST);
                        entries.add(ModItems.BLOOM_DUST);


                        //Blocks
                        entries.add(ModBlocks.ABYSS_LANTERN);

                        entries.add(ModBlocks.BLUE_LANTERN);
                        entries.add(ModBlocks.RED_LANTERN);
                        entries.add(ModBlocks.GREEN_LANTERN);
                        entries.add(ModBlocks.YELLOW_LANTERN);
                        entries.add(ModBlocks.AQUA_LANTERN);

                        entries.add(ModBlocks.REDSTONE_LAMP_ON);

                        entries.add(ModBlocks.SPRING_BLOCK);
                        entries.add(ModBlocks.BLOOM_BLOCK);
                        entries.add(ModBlocks.ADV_BLOOM_BLOCK);

                        entries.add(ModBlocks.ABYSS_LOG);
                        entries.add(ModBlocks.ABYSS_PLANKS);


                        //Flowers
                        entries.add(ModBlocks.ABYSS_FLOWER);
                        entries.add(ModBlocks.ABYSS_PETALS);
                        entries.add(ModBlocks.VIR_FLOWER);
                        entries.add(ModBlocks.VIR_PETALS);
                        entries.add(ModBlocks.SPRING_FLOWER);
                        entries.add(ModBlocks.COMPACTED_FLOWERS);


            }).build());


    public static void registerItemGroups(){
        Azara.LOGGER.info("Registering Item Groups for"+ Azara.MOD_ID);
    }
}
