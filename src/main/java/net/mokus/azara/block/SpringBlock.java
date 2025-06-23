package net.mokus.azara.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mokus.azara.sound.ModSounds;

public class SpringBlock extends Block {

    public static final BooleanProperty POWERED = BooleanProperty.of("powered");

    public SpringBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(POWERED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos fromPos, boolean notify) {
        if (!world.isClient) {
            boolean isPowered = world.isReceivingRedstonePower(pos);
            if (state.get(POWERED) != isPowered) {
                world.setBlockState(pos, state.with(POWERED, isPowered), Block.NOTIFY_ALL);
            }
        }
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!world.isClient && state.get(POWERED)) {
            if (entity instanceof LivingEntity living && !living.isSneaking()) {
                living.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 20, 30, true, false));
                world.playSound(
                        null,
                        entity.getBlockPos(),
                        SoundEvents.BLOCK_PISTON_CONTRACT,
                        SoundCategory.BLOCKS,
                        0.5f,
                        1.0f
                );
            }
        }
        super.onSteppedOn(world, pos, state, entity);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!world.isClient && state.get(POWERED)) {
            entity.handleFallDamage(fallDistance, 0.0F, world.getDamageSources().fall());
        }
    }
}
