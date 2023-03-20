package com.github.cao.awa.trtr.block.stove.mud.model;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.block.TrtrBlocks;
import com.github.cao.awa.trtr.block.stove.mud.MudStove;
import com.github.cao.awa.trtr.framework.accessor.data.gen.model.TrtrBlockModelProvider;
import com.github.cao.awa.trtr.identifier.namespane.Namespace;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.MultipartBlockStateSupplier;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

@Auto
public class MudStoveModelProvider extends TrtrBlockModelProvider {
    @Auto
    public MudStoveModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        Identifier identifier = MudStove.IDENTIFIER;

        variants(VariantSettings.MODEL,
                 Namespace.subSpace(identifier,
                                    "block"
                 )
        );

        MultipartBlockStateSupplier supplier = directions(MultipartBlockStateSupplier.create(TrtrBlocks.get(identifier)));

        blockStateModelGenerator.blockStateCollector.accept(supplier);
    }

    private MultipartBlockStateSupplier directions(MultipartBlockStateSupplier supplier) {
        DirectionProperty facing = MudStove.FACING;

        supplier.with(when(facing,
                           Direction.NORTH
                      ),
                      variants()
                )
                .with(when(facing,
                           Direction.EAST
                      ),
                      variant(VariantSettings.Y,
                              VariantSettings.Rotation.R90
                      )
                )
                .with(when(facing,
                           Direction.SOUTH
                      ),
                      variant(VariantSettings.Y,
                              VariantSettings.Rotation.R180
                      )
                )
                .with(when(facing,
                           Direction.WEST
                      ),
                      variant(VariantSettings.Y,
                              VariantSettings.Rotation.R270
                      )
                );

        return supplier;
    }
}
