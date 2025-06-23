package net.mokus.azara.item;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.mokus.azara.effect.ModEffects;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class ModArmorItem2 extends ArmorItem {

    public ModArmorItem2(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.azara.aina.desc"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
