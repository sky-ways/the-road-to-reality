package com.github.cao.awa.trtr.block.state;

import com.github.cao.awa.trtr.block.state.supplier.StepBlockStateSupplier;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.util.math.Direction;

public class BlockStates {
    public static void horizontalFacing(StepBlockStateSupplier supplier, Direction value) {
        switch (value) {
            case NORTH -> supplier.variant(VariantSettings.Y,
                                           VariantSettings.Rotation.R0
            );
            case EAST -> supplier.variant(VariantSettings.Y,
                                          VariantSettings.Rotation.R90
            );
            case SOUTH -> supplier.variant(VariantSettings.Y,
                                           VariantSettings.Rotation.R180
            );
            case WEST -> supplier.variant(VariantSettings.Y,
                                          VariantSettings.Rotation.R270
            );
        }
    }
}
