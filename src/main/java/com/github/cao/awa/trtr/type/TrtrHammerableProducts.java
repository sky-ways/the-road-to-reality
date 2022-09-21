package com.github.cao.awa.trtr.type;

import com.github.cao.awa.trtr.ref.item.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.table.*;
import net.minecraft.item.*;

public class TrtrHammerableProducts {
    // Trtr item
    public static final RageTable<Item, NumberRage<Item>> CRUSHED_BAUXITE = RageTable.of(NumberRage.absolute(TrtrItems.CRUSHED_BAUXITE, 5));
    public static final RageTable<Item, NumberRage<Item>> CRUSHED_COAL = RageTable.of(NumberRage.absolute(TrtrItems.CRUSHED_COAL, 5));
    public static final RageTable<Item, NumberRage<Item>> CRUSHED_HEMATITE = RageTable.of(NumberRage.absolute(TrtrItems.CRUSHED_HEMATITE, 5));
    public static final RageTable<Item, NumberRage<Item>> CRUSHED_GALENA = RageTable.of(NumberRage.absolute(TrtrItems.CRUSHED_GALENA, 5));
    public static final RageTable<Item, NumberRage<Item>> CRUSHED_ACANTHITE = RageTable.of(NumberRage.absolute(TrtrItems.CRUSHED_ACANTHITE, 5));

    public static final RageTable<Item, NumberRage<Item>> CRUSHED_DEEPSLATE_BAUXITE = RageTable.of(NumberRage.absolute(TrtrItems.CRUSHED_DEEPSLATE_BAUXITE, 5));
    public static final RageTable<Item, NumberRage<Item>> CRUSHED_DEEPSLATE_COAL = RageTable.of(NumberRage.absolute(TrtrItems.CRUSHED_DEEPSLATE_COAL, 5));
    public static final RageTable<Item, NumberRage<Item>> CRUSHED_DEEPSLATE_HEMATITE = RageTable.of(NumberRage.absolute(TrtrItems.CRUSHED_DEEPSLATE_HEMATITE, 5));
    public static final RageTable<Item, NumberRage<Item>> CRUSHED_DEEPSLATE_GALENA = RageTable.of(NumberRage.absolute(TrtrItems.CRUSHED_DEEPSLATE_GALENA, 5));
    public static final RageTable<Item, NumberRage<Item>> CRUSHED_DEEPSLATE_ACANTHITE = RageTable.of(NumberRage.absolute(TrtrItems.CRUSHED_DEEPSLATE_ACANTHITE, 5));
    public static final RageTable<Item, NumberRage<Item>> CRUSHED_STONE = RageTable.of(NumberRage.absolute(TrtrItems.CRUSHED_STONE, 5));
    public static final RageTable<Item, NumberRage<Item>> CRUSHED_DEEPSLATE = RageTable.of(NumberRage.absolute(TrtrItems.CRUSHED_DEEPSLATE, 5));

    public static final RageTable<Item, NumberRage<Item>> BAUXITE_POWDER = RageTable.of(NumberRage.absolute(TrtrItems.BAUXITE_POWDER, 5));
    public static final RageTable<Item, NumberRage<Item>> COAL_POWDER = RageTable.of(NumberRage.absolute(TrtrItems.COAL_POWDER, 5));
    public static final RageTable<Item, NumberRage<Item>> HEMATITE_POWDER = RageTable.of(NumberRage.absolute(TrtrItems.HEMATITE_POWDER, 5));
    public static final RageTable<Item, NumberRage<Item>> GALENA_POWDER = RageTable.of(NumberRage.absolute(TrtrItems.GALENA_POWDER, 5));
    public static final RageTable<Item, NumberRage<Item>> ACANTHITE_POWDER = RageTable.of(NumberRage.absolute(TrtrItems.ACANTHITE_POWDER, 5));
    public static final RageTable<Item, NumberRage<Item>> STONE_POWDER = RageTable.of(NumberRage.absolute(TrtrItems.STONE_POWDER, 5));
    public static final RageTable<Item, NumberRage<Item>> DEEPSLATE_POWDER = RageTable.of(NumberRage.absolute(TrtrItems.DEEPSLATE_POWDER, 5));
    public static final RageTable<Item, NumberRage<Item>> CHALCOPYRITE_POWDER = RageTable.of(NumberRage.absolute(TrtrItems.CHALCOPYRITE_POWDER, 5));
    public static final RageTable<Item, NumberRage<Item>> CUPRITE_POWDER = RageTable.of(NumberRage.absolute(TrtrItems.CUPRITE_POWDER, 5));

    public static void pre() {
        register(TrtrItems.CRUSHED_BAUXITE, CRUSHED_BAUXITE);
        register(TrtrItems.CRUSHED_COAL, CRUSHED_COAL);
        register(TrtrItems.CRUSHED_HEMATITE, CRUSHED_HEMATITE);
        register(TrtrItems.CRUSHED_GALENA, CRUSHED_GALENA);
        register(TrtrItems.CRUSHED_ACANTHITE, CRUSHED_ACANTHITE);
        register(TrtrItems.CRUSHED_STONE, CRUSHED_STONE);
        register(TrtrItems.CRUSHED_DEEPSLATE, CRUSHED_DEEPSLATE);

        register(TrtrItems.CRUSHED_DEEPSLATE_BAUXITE, CRUSHED_DEEPSLATE_BAUXITE);
        register(TrtrItems.CRUSHED_DEEPSLATE_COAL, CRUSHED_DEEPSLATE_COAL);
        register(TrtrItems.CRUSHED_DEEPSLATE_HEMATITE, CRUSHED_DEEPSLATE_HEMATITE);
        register(TrtrItems.CRUSHED_DEEPSLATE_GALENA, CRUSHED_DEEPSLATE_GALENA);
        register(TrtrItems.CRUSHED_DEEPSLATE_ACANTHITE, CRUSHED_DEEPSLATE_ACANTHITE);

        register(TrtrItems.BAUXITE_POWDER, BAUXITE_POWDER);
        register(TrtrItems.COAL_POWDER, COAL_POWDER);
        register(TrtrItems.HEMATITE_POWDER, HEMATITE_POWDER);
        register(TrtrItems.GALENA_POWDER, GALENA_POWDER);
        register(TrtrItems.ACANTHITE_POWDER, ACANTHITE_POWDER);
        register(TrtrItems.STONE_POWDER, STONE_POWDER);
        register(TrtrItems.DEEPSLATE_POWDER, DEEPSLATE_POWDER);
        register(TrtrItems.CHALCOPYRITE_POWDER, CHALCOPYRITE_POWDER);
        register(TrtrItems.CUPRITE_POWDER, CUPRITE_POWDER);
    }

    public static void register(Item item, RageTable<Item, NumberRage<Item>> table) {
        if (item instanceof Hammerable hammerable) {
            TrtrHammerables.register(hammerable.prototypes(), table);
        }
    }
}

