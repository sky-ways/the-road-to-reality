package com.github.cao.awa.trtr.criteria;

import com.github.cao.awa.trtr.criteria.handcraft.HandcraftedItemCriterion;
import net.minecraft.advancement.criterion.Criteria;

public class TrtrCriteria {
    public static final HandcraftedItemCriterion HANDCRAFTED = Criteria.register("trtr:handcrafted",
                                                                                 new HandcraftedItemCriterion()
    );

    public static void initialize() {

    }
}
