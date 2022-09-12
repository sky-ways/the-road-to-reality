package com.github.cao.awa.trtr.tool.hammer;

import com.github.cao.awa.trtr.type.*;
import com.google.common.collect.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public abstract class Hammer extends TrtrToolItem {
    private static final ObjectArrayList<Hammer> registered = new ObjectArrayList<>();
    private final float attackDamage;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public Hammer(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(settings.maxDamage(material.getDurability()));
        registered.add(this);
        this.attackDamage = attackDamage + material.getAttackDamage();
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Tool modifier", this.attackDamage, EntityAttributeModifier.Operation.ADDITION));
        builder.put(TrtrEntityAttributes.THUMP_EFFICIENCY, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Tool modifier", attackSpeed, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (state.getHardness(world, pos) != 0.0F) {
            stack.damage(2, miner, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }

        return true;
    }

    public static Settings defaultSettings() {
        return new Settings().group(ItemGroup.TOOLS);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        return true;
    }

    public TypedActionResult<ItemStack> thump(World world, PlayerEntity user, Hand hand) {
        for (Hammer hammer : registered) {
            user.getItemCooldownManager().set(hammer, 20);
        }
        user.swingHand(hand, true);
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? attributeModifiers : super.getAttributeModifiers(slot);
    }
}
