package com.github.cao.awa.trtr.block.pebble;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.block.TrtrBlocks;
import com.github.cao.awa.trtr.block.state.BlockStates;
import com.github.cao.awa.trtr.block.state.supplier.StepBlockStateSupplier;
import com.github.cao.awa.trtr.framework.accessor.data.gen.model.TrtrBlockModelProvider;
import com.github.cao.awa.trtr.identifier.namespane.Namespace;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

@Auto
public class PebbleBlockModel extends TrtrBlockModelProvider {
    @Auto
    public PebbleBlockModel(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        Identifier identifier = PebbleBlock.IDENTIFIER;

        StepBlockStateSupplier supplier = directions(StepBlockStateSupplier.create(TrtrBlocks.get(identifier)));

        blockStateModelGenerator.blockStateCollector.accept(supplier);
    }

    private StepBlockStateSupplier directions(StepBlockStateSupplier supplier) {
        supplier.property(PebbleBlock.FACING)
                .property(PebbleBlock.COUNT)
                .property(PebbleBlock.TYPE);

        supplier.changer(map -> {
            BlockStates.horizontalFacing(supplier,
                                         (Direction) map.get(PebbleBlock.FACING)
                                                        .value()
            );

            int type = EntrustEnvironment.cast(map.get(PebbleBlock.TYPE)
                                                  .value());
            int count = EntrustEnvironment.cast(map.get(PebbleBlock.COUNT)
                                                   .value());

            if (type == 1 && count == 3) {
                return false;
            }

            supplier.variant(VariantSettings.MODEL,
                             Namespace.subSpace(Identifier.tryParse("trtr:pebble_block_" + type + "_" + count),
                                                "block"
                             )
            );

            return true;
        });

        return supplier;
    }
}
