package com.github.cao.awa.trtr.type;

import com.github.cao.awa.trtr.container.bowl.wooden.water.*;
import com.github.cao.awa.trtr.container.bucket.wooden.*;
import com.github.cao.awa.trtr.debuger.*;
import com.github.cao.awa.trtr.ore.aluminum.bauxite.crushed.*;
import com.github.cao.awa.trtr.ore.aluminum.bauxite.powder.*;
import com.github.cao.awa.trtr.ore.carbon.coal.crushed.*;
import com.github.cao.awa.trtr.ore.carbon.coal.powder.*;
import com.github.cao.awa.trtr.ore.iron.hematite.crushed.*;
import com.github.cao.awa.trtr.ore.iron.hematite.powder.*;
import com.github.cao.awa.trtr.ore.lead.galena.crushed.*;
import com.github.cao.awa.trtr.ore.lead.galena.powder.*;
import com.github.cao.awa.trtr.ore.silver.acanthite.crushed.*;
import com.github.cao.awa.trtr.ore.silver.acanthite.powder.*;
import com.github.cao.awa.trtr.ore.stone.crushed.*;
import com.github.cao.awa.trtr.ore.stone.powder.*;
import com.github.cao.awa.trtr.plant.fibres.*;
import com.github.cao.awa.trtr.power.storage.battery.*;
import com.github.cao.awa.trtr.tool.hammer.iron.*;
import com.github.cao.awa.trtr.tool.hammer.stone.*;
import com.github.cao.awa.trtr.tool.hammer.wooden.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.fluid.*;
import net.minecraft.item.*;

public class TrtrItems {
    public static final ObjectArrayList<Item> items = new ObjectArrayList<>();
    public static final Item BATTERY = Battery.register();
    public static final Item THERMOMETER = HandHoldThermometer.register();
    public static final Item IRON_HAMMER = IronHammer.register();
    public static final Item STONE_HAMMER = StoneHammer.register();
    public static final Item WOODEN_HAMMER = WoodenHammer.register();

    public static final Item WOODEN_BOWL_WATERED = WoodenBowlWateredItem.register();

    public static final Item WOODEN_BUCKET = WoodenBucket.register(WoodenBucket.IDENTIFIER, Fluids.EMPTY);
    public static final Item WATER_WOODEN_BUCKET = WoodenBucket.register(WoodenBucket.WATER_IDENTIFIER, Fluids.WATER);

    public static final Item PLANT_FIBER = PlantFibre.register();

    public static final Item BAUXITE_POWDER = BauxitePowder.register();
    public static final Item ACANTHITE_POWDER = AcanthitePowder.register();
    public static final Item GALENA_POWDER = GalenaPowder.register();
    public static final Item COAL_POWDER = CoalPowder.register();
    public static final Item HEMATITE_POWDER = HematitePowder.register();
    public static final Item DEEPSLATE_POWDER = DeepslatePowder.register();
    public static final Item STONE_POWDER = StonePowder.register();

    public static final Item CRUSHED_BAUXITE = CrushedBauxite.register();
    public static final Item CRUSHED_ACANTHITE = CrushedAcanthite.register();
    public static final Item CRUSHED_GALENA = CrushedGalena.register();
    public static final Item CRUSHED_COAL = CrushedCoal.register();
    public static final Item CRUSHED_HEMATITE = CrushedHematite.register();

    public static final Item CRUSHED_DEEPSLATE_BAUXITE = CrushedDeepslateBauxite.register();
    public static final Item CRUSHED_DEEPSLATE_ACANTHITE = CrushedDeepslateAcanthite.register();
    public static final Item CRUSHED_DEEPSLATE_GALENA = CrushedDeepslateGalena.register();
    public static final Item CRUSHED_DEEPSLATE_COAL = CrushedDeepslateCoal.register();
    public static final Item CRUSHED_DEEPSLATE_HEMATITE = CrushedDeepslateHematite.register();

    public static final Item CRUSHED_DEEPSLATE = CrushedDeepslate.register();
    public static final Item CRUSHED_STONE = CrushedStone.register();

    public static void pre() {
    }
}
