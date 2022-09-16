package com.github.cao.awa.trtr.ore.carbon.coal.crushed;

import com.github.cao.awa.trtr.ref.item.*;
import com.github.cao.awa.trtr.ref.item.trtr.*;
import com.github.cao.awa.trtr.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.table.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

import java.util.*;

public class CrushedDeepslateCoal extends TrtrItem implements Hammerable {
    public static final Identifier IDENTIFIER = new Identifier("trtr:crushed_deepslate_coal");

    public CrushedDeepslateCoal(Settings settings) {
        super(settings);
    }

    public static Item register() {
        Settings settings = new Settings();
        CrushedDeepslateCoal coal = new CrushedDeepslateCoal(settings);
        Registry.register(Registry.ITEM, IDENTIFIER, coal);
        return coal;
    }

    @Override
    public RageTable<Item, NumberRage<Item>> products() {
        return TrtrHammerableProducts.COAL_POWDER;
    }

    @Override
    public Set<Item> prototypes() {
        return Set.of(Items.DEEPSLATE_COAL_ORE);
    }
}
