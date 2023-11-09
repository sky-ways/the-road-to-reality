package com.github.cao.awa.trtr.item.stone;

import com.github.cao.awa.trtr.item.TrtrItems;
import com.github.cao.awa.trtr.item.part.stone.*;
import com.github.cao.awa.trtr.math.Mathematics;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class StonePartCraft {
    public static ItemStack craft(int currentCraftTime) {
        return new ItemStack(craftComponent(currentCraftTime),
                             1
        );
    }

    private static Item craftComponent(int currentCraftTime) {
        if (Mathematics.inRange(currentCraftTime,
                                0,
                                40
        )) {
            return TrtrItems.get(StoneHammerPartItem.class);
        } else if (Mathematics.inRange(currentCraftTime,
                                       40,
                                       60
        )) {
            return TrtrItems.get(StonePickaxePartItem.class);
        } else if (Mathematics.inRange(currentCraftTime,
                                       60,
                                       80
        )) {
            return TrtrItems.get(StoneAxePartItem.class);
        } else if (Mathematics.inRange(currentCraftTime,
                                       80,
                                       100
        )) {
            return TrtrItems.get(StoneSwordPartItem.class);
        } else if (Mathematics.inRange(currentCraftTime,
                                       100,
                                       120
        )) {
            return TrtrItems.get(StoneShovelPartItem.class);
        } else if (Mathematics.inRange(currentCraftTime,
                                       120,
                                       140
        )) {
            return TrtrItems.get(StoneHoePartItem.class);
        } else if (Mathematics.inRange(currentCraftTime,
                                       140,
                                       160
        )) {
            return TrtrItems.get(StoneHiltPartItem.class);
        }
        {
            // 160 ~ 170
            return TrtrItems.get(StoneKnifePartItem.class);
        }
    }
}
