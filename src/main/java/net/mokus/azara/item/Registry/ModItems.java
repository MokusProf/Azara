package net.mokus.azara.item.Registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mokus.azara.Azara;
import net.mokus.azara.item.*;
import net.mokus.azara.item.groups.ModArmorItem;
import net.mokus.azara.item.groups.ModArmorMaterials;
import net.mokus.azara.item.groups.ModToolMaterial;
import net.mokus.azara.item.useonce.AbyssCallSword;
import net.mokus.azara.item.useonce.BloomSword;
import net.mokus.azara.item.useonce.RageSword;


public class ModItems {
    //Items goes here :3

    //Consumables

    public static final Item ABYSS_DUST = registerItem("abyss_dust",
            new Item(new FabricItemSettings().food(ModFoodComponents.ABYSS_DUST)));

    public static final Item BLOOM_DUST = registerItem("bloom_dust",
            new Item(new FabricItemSettings().food(ModFoodComponents.BLOOM_DUST)));


    // Weapons

    public static final Item ABYSSAL_SWORD = registerItem("abyssal_sword",
            new AbyssCallSword(ModToolMaterial.ABYSS,6,-2.4F, new FabricItemSettings().maxDamage(1)));

    public static final Item BLOOM_SWORD = registerItem("bloom_sword",
            new BloomSword(ModToolMaterial.ABYSS,5,-2.4f, new FabricItemSettings().maxDamage(1)));

    public static final Item GREATER_RAGE_SWORD = registerItem("greater_rage_sword",
            new LongSword(ModToolMaterial.ABYSS_NETHERITE,0,-3.0f, new FabricItemSettings().maxDamage(1)));

    public static final Item GREATER_BLOOM_SWORD = registerItem("greater_bloom_sword",
            new LongSword(ToolMaterials.DIAMOND,4,-2.8f, new FabricItemSettings().maxDamage(1)));

    public static final Item RAGE_SWORD = registerItem("rage_sword",
            new RageSword(ToolMaterials.NETHERITE,3,-2.4f,new FabricItemSettings().maxDamage(1)));

    //Normal Items

    public static final Item ABYSS_NETHERITE = registerItem("abyss_netherite",
            new Item(new FabricItemSettings()));

    public static final Item BLOOM_GLOBE = registerItem("bloom_globe",
            new Item(new FabricItemSettings()));

    //Armor

    public static final Item ABYSS_NETHERITE_HELMET = registerItem("abyss_netherite_helmet",
            new ModArmorItem(ModArmorMaterials.ABYSS_NETHERITE, ArmorItem.Type.HELMET, new FabricItemSettings()));

    public static final Item ABYSS_NETHERITE_CHESTPLATE = registerItem("abyss_netherite_chestplate",
            new ModArmorItem2(ModArmorMaterials.ABYSS_NETHERITE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));

    public static final Item ABYSS_NETHERITE_LEGGINGS = registerItem("abyss_netherite_leggings",
            new ModArmorItem2(ModArmorMaterials.ABYSS_NETHERITE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));

    public static final Item ABYSS_NETHERITE_BOOTS = registerItem("abyss_netherite_boots",
            new ModArmorItem2(ModArmorMaterials.ABYSS_NETHERITE, ArmorItem.Type.BOOTS, new FabricItemSettings()));



    //Probably getting removed


    //public static final Item PARTICLE_STICK = registerItem("particle_stick",
    //    new ParticleSpawnItem(new FabricItemSettings()));

    //Code goes here
    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries){

    }

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Azara.MOD_ID, name), item);
    }


    public static void registerModItems() {
        Azara.LOGGER.info("Registering Mod Items for"+ Azara.MOD_ID );

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
