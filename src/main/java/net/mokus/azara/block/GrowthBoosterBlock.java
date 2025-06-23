package net.mokus.azara.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GrowthBoosterBlock extends Block {
    public static final BooleanProperty POWERED = Properties.POWERED;
    private static final int GROW_RADIUS = 15;
    private static final int TICK_PERIOD = 300;

    public GrowthBoosterBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(POWERED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock,
                               BlockPos sourcePos, boolean notify) {
        if (!world.isClient) {
            boolean hasPower = world.isReceivingRedstonePower(pos);
            boolean wasPowered = state.get(POWERED);
            if (hasPower && !wasPowered) {
                world.setBlockState(pos, state.with(POWERED, true), Block.NOTIFY_LISTENERS);
                world.scheduleBlockTick(pos, this, TICK_PERIOD);
            } else if (!hasPower && wasPowered) {
                world.setBlockState(pos, state.with(POWERED, false), Block.NOTIFY_LISTENERS);
            }
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(POWERED)) {
            applyGrowthBoost(world, pos, random);
            world.scheduleBlockTick(pos, this, TICK_PERIOD);
        }
    }

    private void applyGrowthBoost(ServerWorld world, BlockPos center, Random random) {
        BlockPos.streamOutwards(center, GROW_RADIUS, 2, GROW_RADIUS).forEach(targetPos -> {
            BlockState targetState = world.getBlockState(targetPos);
            if (targetState.getBlock() instanceof CropBlock cropBlock) {
                cropBlock.grow(world, random, targetPos, targetState);
            }
        });

        world.spawnParticles(
                ParticleTypes.HAPPY_VILLAGER,
                center.getX() + 0.5,
                center.getY() + 1.2,
                center.getZ() + 0.5,
                5, 0.2, 0.2, 0.2, 0.01
        );
    }
}