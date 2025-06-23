package net.mokus.azara.entity;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.mokus.azara.effect.ModEffects;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

public class BloomDisplayBlock extends BlockWithEntity {
    public BloomDisplayBlock(Settings settings) {
        super(settings);
    }
    private static final int RADIUS = 50;
    private static final int TICK_INTERVAL = 80;
    private static final int EFFECT_DURATION = 100;

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BloomDisplayBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!world.isClient) {
            world.scheduleBlockTick(pos, this, TICK_INTERVAL);
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient) {
            List<PlayerEntity> players = world.getEntitiesByClass(
                    PlayerEntity.class,
                    new Box(pos).expand(RADIUS),
                    player -> true
            );

            for (PlayerEntity player : players) {
                player.addStatusEffect(new StatusEffectInstance(ModEffects.BLOOMEFFECT, EFFECT_DURATION, 0, false, true));
            }


            world.scheduleBlockTick(pos, this, TICK_INTERVAL);
        }

    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable("block.azara.abb.desc"));
        super.appendTooltip(stack, world, tooltip, options);
    }
}

