package net.mokus.azara.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mokus.azara.block.ModBlocks;

public class ModBlockEntities {
    public static final BlockEntityType<BloomDisplayBlockEntity> BLOOM_DISPLAY_BLOCK_ENTITY =
            Registry.register(
                    Registries.BLOCK_ENTITY_TYPE,
                    new Identifier("azara", "bloom_display_block_entity"),
                    FabricBlockEntityTypeBuilder.create(BloomDisplayBlockEntity::new, ModBlocks.ADV_BLOOM_BLOCK).build(null)
            );
    public static void register() {
        // intentionally left empty; registration happens above
    }
}
