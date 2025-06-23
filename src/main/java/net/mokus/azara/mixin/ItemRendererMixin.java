package net.mokus.azara.mixin;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.mokus.azara.Azara;
import net.mokus.azara.item.Registry.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel useLongSwordModel(
            BakedModel originalModel,
            ItemStack stack,
            ModelTransformationMode renderMode,
            boolean leftHanded,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            int overlay
    ) {
        if (renderMode == ModelTransformationMode.GUI) {
            return originalModel;
        }

        int modelStage = stack.getOrCreateNbt().getInt("CustomModelData");
        if (modelStage == 0) {
            if (stack.isOf(ModItems.GREATER_RAGE_SWORD)) {
                return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager()
                        .getModel(new ModelIdentifier(Azara.MOD_ID, "greater_rage_sword_alt", "inventory"));
            } else if (stack.isOf(ModItems.RAGE_SWORD)) {
                return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager()
                        .getModel(new ModelIdentifier(Azara.MOD_ID, "rage_sword_alt", "inventory"));
            } else if (stack.isOf(ModItems.BLOOM_SWORD)) {
                return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager()
                        .getModel(new ModelIdentifier(Azara.MOD_ID, "bloom_sword_alt", "inventory"));
            } else if (stack.isOf(ModItems.GREATER_BLOOM_SWORD)) {
                return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager()
                        .getModel(new ModelIdentifier(Azara.MOD_ID, "greater_bloom_sword_alt", "inventory"));
            }
        }
        return originalModel;
    }
}

