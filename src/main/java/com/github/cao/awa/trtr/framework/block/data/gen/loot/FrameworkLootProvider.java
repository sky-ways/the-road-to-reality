package com.github.cao.awa.trtr.framework.block.data.gen.loot;

import com.github.cao.awa.trtr.framework.accessor.data.gen.loot.LootFactory;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.BiConsumer;

public class FrameworkLootProvider extends SimpleFabricLootTableProvider {
    private final List<LootFactory<?>> factories;

    public FrameworkLootProvider(FabricDataOutput output, List<LootFactory<?>> factories) {
        super(output,
              LootContextTypes.BLOCK
        );
        this.factories = factories;
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> biConsumer) {
        for (LootFactory<?> factory : this.factories) {
            EntrustEnvironment.notNull(EntrustEnvironment.cast(factory.create(this.output),
                                                               FabricLootTableProvider.class
                                       ),
                                       provider -> provider.accept(biConsumer)
            );
        }
    }
}