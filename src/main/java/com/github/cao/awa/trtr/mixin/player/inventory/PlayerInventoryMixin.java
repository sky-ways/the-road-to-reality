package com.github.cao.awa.trtr.mixin.player.inventory;

import com.github.cao.awa.trtr.database.properties.*;
import com.github.cao.awa.trtr.dev.dump.mixin.player.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.collection.*;
import net.minecraft.util.crash.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import static com.github.cao.awa.trtr.TrtrMod.propertiesDatabase;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin implements Inventory, Nameable {
    @Shadow public abstract int getEmptySlot();

    @Shadow @Final public DefaultedList<ItemStack> main;

    @Shadow @Final public PlayerEntity player;

    @Shadow protected abstract int addStack(ItemStack stack);

    @Shadow protected abstract int addStack(int slot, ItemStack stack);

    @Shadow public abstract ItemStack getStack(int slot);

    /**
     * @author cao_awa
     */
    @Overwrite
    public boolean insertStack(int slot, ItemStack stack) {
//        System.out.println("insert...");
        if (stack.isEmpty()) {
            return false;
        } else {
            try {
                if (stack.isDamaged()) {
                    if (slot == -1) {
                        slot = this.getEmptySlot();
                    }

                    if (slot > -1) {
                        this.main.set(slot, stack.copy());
                        this.main.get(slot)
                                 .setBobbingAnimationTime(5);
                        stack.setCount(0);
                        return true;
                    } else if (this.player.getAbilities().creativeMode) {
                        stack.setCount(0);
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    int i;
                    do {
                        i = stack.getCount();
                        if (slot == -1) {
                            stack.setCount(this.addStack(stack));
                        } else {
                            stack.setCount(this.addStack(slot, stack));
                        }
                    } while(!stack.isEmpty() && stack.getCount() < i);

                    if (stack.getCount() == i && this.player.getAbilities().creativeMode) {
                        stack.setCount(0);
                        return true;
                    } else {
                        return stack.getCount() < i;
                    }
                }
            } catch (Throwable var6) {
                CrashReport crashReport = CrashReport.create(var6, "Adding item to inventory");
                CrashReportSection crashReportSection = crashReport.addElement("Item being added");
                crashReportSection.add("Item ID", Item.getRawId(stack.getItem()));
                crashReportSection.add("Item data", stack.getDamage());
                crashReportSection.add("Item name", () -> {
                    return stack.getName().getString();
                });
                throw new CrashException(crashReport);
            }
        }
    }

    @Inject(method = "addStack(ILnet/minecraft/item/ItemStack;)I", at = @At("HEAD"), cancellable = true)
    public void addStack(int slot, ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(mergeData(slot, stack));
    }

    private int mergeData(int slot, ItemStack source) {
        ItemStack target = this.getStack(slot);
        if (target.isEmpty()) {
            target = new ItemStack(source.getItem(), 0);
            if (source.hasNbt()) {
                target.setNbt(source.getNbt().copy());
            }

            this.setStack(slot, target);
        }

        int i = source.getCount();

        int j = Math.min(
                i,
                target.getMaxCount() - target.getCount()
        );

        if (j > this.getMaxCountPerStack() - target.getCount()) {
            j = this.getMaxCountPerStack() - target.getCount();
        }

        if (j != 0) {
            i -= j;
            target.increment(j);
            target.setBobbingAnimationTime(5);
        }

        if (target.hasNbt()) {
            InstanceProperties properties = propertiesDatabase.get(target.getNbt()
                                                                         .getString("acs"));
            if (properties != null) {
                PlayerInventoryMixinDev.mergeData(properties);
            }
        }

        return i;
    }
}
