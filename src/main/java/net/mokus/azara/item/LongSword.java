package net.mokus.azara.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SmithingTableBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mokus.azara.effect.ModEffects;
import net.mokus.azara.item.Registry.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class LongSword extends SwordItem implements DamageTrackingWeapon{

    private static final UUID REACH_MODIFIER_ID = UUID.fromString("d21cf99e-47f4-11ec-81d3-0242ac130003");
    private static final UUID ATTACK_RANGE_MODIFIER_ID = UUID.fromString("d21cfa62-47f4-11ec-81d3-0242ac130003");



    private static final String DAMAGE_KEY = "DamageDealt";
    public static final int DAMAGE_THRESHOLD = 60; // 25 hearts



    public LongSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }


    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        Multimap<EntityAttribute, EntityAttributeModifier> baseModifiers = super.getAttributeModifiers(slot);


        if (slot == EquipmentSlot.MAINHAND) {
            ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
            builder.putAll(baseModifiers);

            builder.put(ReachEntityAttributes.REACH,
                    new EntityAttributeModifier(REACH_MODIFIER_ID, "Reach modifier", 1.5, EntityAttributeModifier.Operation.ADDITION));
            builder.put(ReachEntityAttributes.ATTACK_RANGE,
                    new EntityAttributeModifier(ATTACK_RANGE_MODIFIER_ID, "Attack range modifier", 0.75, EntityAttributeModifier.Operation.ADDITION));

            return builder.build();
        }

        return baseModifiers;
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
                HitResult hitResult = user.raycast(5.0D, 0.0F, false);
                if (hitResult.getType() == HitResult.Type.BLOCK) {
                    return TypedActionResult.pass(stack);
                }


            int damageDealt = getDamageDealt(stack);

            if (damageDealt >= DAMAGE_THRESHOLD/2) {
                if(damageDealt >= DAMAGE_THRESHOLD) {
                    setDamageDealt(stack, DAMAGE_THRESHOLD/2);
                    if(stack.getItem() == ModItems.GREATER_RAGE_SWORD)
                    user.addStatusEffect(new StatusEffectInstance(ModEffects.RAGEEFFECT,100,0));
                    else if (stack.getItem() == ModItems.GREATER_BLOOM_SWORD) {
                        user.addStatusEffect(new StatusEffectInstance(ModEffects.BLOOMEFFECT,100,1));
                    }
                    return TypedActionResult.success(stack);
                }
                else {
                    setDamageDealt(stack, damageDealt- DAMAGE_THRESHOLD/2);
                    if(stack.getItem() == ModItems.GREATER_RAGE_SWORD)
                        user.addStatusEffect(new StatusEffectInstance(ModEffects.RAGEEFFECT,100,0));
                    else if (stack.getItem() == ModItems.GREATER_BLOOM_SWORD) {
                        user.addStatusEffect(new StatusEffectInstance(ModEffects.BLOOMEFFECT,100,0));
                    }
                    return TypedActionResult.success(stack);
                }
            }
        } else {
        }
        return TypedActionResult.pass(stack);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity user = context.getPlayer();
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        ItemStack stack = context.getStack();


        if (!world.isClient && user != null && user.isSneaking()) {
            BlockState state = world.getBlockState(pos);
            if (state.getBlock() == Blocks.SMITHING_TABLE) {

                int currentStage = stack.getOrCreateNbt().getInt("CustomModelData");
                int nextStage = (currentStage + 1) % 5;

                stack.getOrCreateNbt().putInt("CustomModelData", nextStage);
                user.setStackInHand(context.getHand(), stack.copy());

                user.sendMessage(Text.literal("Sword style has changed!").formatted(Formatting.GRAY), true);

                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    private int getDamageDealt(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        return nbt.getInt(DAMAGE_KEY);
    }

    private void setDamageDealt(ItemStack stack, int amount) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putInt(DAMAGE_KEY, amount);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        int damage = getDamageDealt(stack);
        float progress = Math.min(1.0f, damage / (float) DAMAGE_THRESHOLD);
        return Math.round(progress * 13);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        int damage = getDamageDealt(stack);

        if (damage >= DAMAGE_THRESHOLD) {
            return (191 << 16) | (64 << 8) | 255;
        } else if (damage >= DAMAGE_THRESHOLD / 2){
            return (0 << 16) | (191 << 8) | 255;
        } else {
            return (128 << 16) | (128 << 8) | 128;
        }
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.azara.dtw.desc"));
        if(stack.getItem() == ModItems.GREATER_RAGE_SWORD)
            tooltip.add(Text.translatable("item.azara.greater_rage_sword.desc"));
        else if (stack.getItem() == ModItems.GREATER_BLOOM_SWORD) {
            tooltip.add(Text.translatable("item.azara.greater_bloom_sword.desc"));
        }
        if (stack.hasNbt()) {
            int modelStage = stack.getNbt().getInt("CustomModelData");

            if (modelStage > 0) {
                String appearanceName = switch (modelStage) {
                    case 1 -> "Folly Tree Branch";
                    case 2 -> "Arasaka";
                    case 3 -> "Count Dooku";
                    case 4 -> "Think Fast Chucklenuts";
                    default -> "Unknown";
                };

                tooltip.add(Text.literal("Style: " + appearanceName).formatted(Formatting.LIGHT_PURPLE));
            }
        }

        super.appendTooltip(stack, world, tooltip, context);
    }

}
