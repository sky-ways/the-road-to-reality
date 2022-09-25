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

import java.util.*;

public abstract class Hammer extends TrtrToolItem {
    public static final UUID THUMP_EFFICIENCY = UUID.fromString("2508d652-eafd-4e03-86a0-507a4f4975e5");
    private static final ObjectArrayList<Hammer> registered = new ObjectArrayList<>();
    private final float thumpEfficiency;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public Hammer(ToolMaterial material, int attackDamage, float attackSpeed, float thumpEfficiency) {
        this(material, attackDamage, attackSpeed, thumpEfficiency, new Settings());
    }

    public Hammer(ToolMaterial material, int attackDamage, float attackSpeed, float thumpEfficiency, Settings settings) {
        super(settings.maxDamage(material.getDurability()));
        registered.add(this);
        this.thumpEfficiency = thumpEfficiency;
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Tool modifier", attackDamage + material.getAttackDamage(), EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Tool modifier", attackSpeed, EntityAttributeModifier.Operation.ADDITION));
        builder.put(TrtrEntityAttributes.THUMP_EFFICIENCY, new EntityAttributeModifier(THUMP_EFFICIENCY, "Tool modifier", thumpEfficiency, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    public static Settings defaultSettings() {
        return new Settings().group(ItemGroup.TOOLS);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        return true;
    }

    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (state.getHardness(world, pos) != 0.0F) {
            stack.damage(2, miner, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }

        return true;
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? attributeModifiers : super.getAttributeModifiers(slot);
    }

    public float getThumpEfficiency() {
        return thumpEfficiency;
    }

    public TypedActionResult<ItemStack> thump(World world, PlayerEntity user, Hand hand) {
        for (Hammer hammer : registered) {
            user.getItemCooldownManager().set(hammer, 20);
        }
        user.swingHand(hand, true);
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }
}
