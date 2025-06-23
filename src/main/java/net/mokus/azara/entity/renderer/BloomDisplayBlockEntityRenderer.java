package net.mokus.azara.entity.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;
import net.mokus.azara.entity.BloomDisplayBlockEntity;
import net.mokus.azara.item.Registry.ModItems;

@Environment(EnvType.CLIENT)
public class BloomDisplayBlockEntityRenderer implements BlockEntityRenderer<BloomDisplayBlockEntity> {

    public BloomDisplayBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(BloomDisplayBlockEntity entity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        matrices.translate(0.5, 1.5, 0.5);

        long time = entity.getWorld() != null ? entity.getWorld().getTime() : 0;
        float rotation = (time + tickDelta) % 360;
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotation));

        matrices.scale(1.0f, 1.0f, 1.0f);

        MinecraftClient.getInstance().getItemRenderer().renderItem(
                new ItemStack(ModItems.BLOOM_GLOBE),
                ModelTransformationMode.GROUND,
                light, overlay, matrices, vertexConsumers, entity.getWorld(), 0
        );

        matrices.pop();
    }
}
