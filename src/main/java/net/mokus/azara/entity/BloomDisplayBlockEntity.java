package net.mokus.azara.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class BloomDisplayBlockEntity extends BlockEntity {
    public BloomDisplayBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLOOM_DISPLAY_BLOCK_ENTITY, pos, state);
    }
}
