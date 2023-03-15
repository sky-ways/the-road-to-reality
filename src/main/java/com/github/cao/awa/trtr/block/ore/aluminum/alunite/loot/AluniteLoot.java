package com.github.cao.awa.trtr.block.ore.aluminum.alunite.loot;

import com.github.cao.awa.apricot.anntations.Auto;
import com.github.cao.awa.trtr.annotations.DataGen;
import com.github.cao.awa.trtr.framework.data.gen.loot.TrtrLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.loot.LootTable;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

@Auto
@DataGen
public class AluniteLoot extends TrtrLootTableProvider {
    public AluniteLoot(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> biConsumer) {
        System.out.println("Generate alunite...");
    }
}
