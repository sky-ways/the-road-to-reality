package com.github.cao.awa.trtr.item.pebble;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.dev.OffhandUtil;
import com.github.cao.awa.trtr.item.TrtrItem;
import com.github.cao.awa.trtr.item.TrtrItems;
import com.github.cao.awa.trtr.item.branch.BranchItem;
import com.github.cao.awa.trtr.random.Randoms;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.function.Consumer;

@Auto
public class PebbleItem extends TrtrItem {
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:pebble");

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        Consumer<ItemStack> action = useItem -> {
            NbtCompound nbt = useItem.getOrCreateNbt();
            int carved = nbt.getInt("carve");
            if (carved == 4) {
                useItem.decrement(1);
                nbt.putInt("carve",
                           0
                );
                ItemStack stick = new ItemStack(Items.STICK);
                if (! user.getInventory()
                          .insertStack(stick)) {
                    world.spawnEntity(new ItemEntity(world,
                                                     user.getX(),
                                                     user.getY(),
                                                     user.getZ(),
                                                     stick,
                                                     Randoms.d(0.15),
                                                     Randoms.d(0.1),
                                                     Randoms.d(0.15)
                    ));
                }
                return;
            }
            nbt.putInt("carve",
                       carved + 1
            );
            useItem.setNbt(nbt);
        };

        if (hand == Hand.MAIN_HAND) {
            OffhandUtil.useOff(user.getMainHandStack(),
                               user.getOffHandStack(),
                               (item1, item2) -> ! item2.isEmpty() && item2.getItem() == TrtrItems.get(BranchItem.IDENTIFIER),
                               action
            );
        } else {
            OffhandUtil.useMain(user.getMainHandStack(),
                                user.getOffHandStack(),
                                (item1, item2) -> ! item1.isEmpty() && item1.getItem() == TrtrItems.get(BranchItem.IDENTIFIER),
                                action
            );
        }

        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
