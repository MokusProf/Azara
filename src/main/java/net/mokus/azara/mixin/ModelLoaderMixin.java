package net.mokus.azara.mixin;

import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.mokus.azara.Azara;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {
    @Shadow
    protected abstract void addModel(ModelIdentifier modelId);

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/model/ModelLoader;addModel(Lnet/minecraft/client/util/ModelIdentifier;)V", ordinal = 3, shift = At.Shift.AFTER))
    public void addLongSword(BlockColors blockColors, Profiler profiler, Map<Identifier, JsonUnbakedModel> jsonUnbakedModels, Map<Identifier, List<ModelLoader.SourceTrackedData>> blockStates, CallbackInfo ci) {
        this.addModel(new ModelIdentifier(Azara.MOD_ID, "greater_rage_sword_alt", "inventory"));
        this.addModel(new ModelIdentifier(Azara.MOD_ID, "rage_sword_alt", "inventory"));
        this.addModel(new ModelIdentifier(Azara.MOD_ID, "bloom_sword_alt", "inventory"));
        this.addModel(new ModelIdentifier(Azara.MOD_ID, "greater_bloom_sword_alt", "inventory"));
        this.addModel(new ModelIdentifier(Azara.MOD_ID, "bs_folly_ver", "inventory"));
    }
}
