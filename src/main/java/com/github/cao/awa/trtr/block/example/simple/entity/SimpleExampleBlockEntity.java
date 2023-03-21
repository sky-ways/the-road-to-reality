package com.github.cao.awa.trtr.block.example.simple.entity;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.annotation.serializer.AutoNbt;
import com.github.cao.awa.trtr.block.stove.mud.MudStoveBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Auto
public class SimpleExampleBlockEntity extends BlockEntity {
    // Write to nbt with 'specify_key' like:
    // {
    //     x: 0,
    //     y: 0,
    //     z: 0,
    //     id: "trtr:example_simple",
    //     specify_key: 114514
    // }
    @AutoNbt("specify_key")
    private int testInteger = 114514;
    // Write to nbt with the field name like:
    // {
    //     x: 0,
    //     y: 0,
    //     z: 0,
    //     id: "trtr:example_simple",
    //     testString: "www"
    // }
    @AutoNbt
    private String testString = "www";

    public SimpleExampleBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type,
              pos,
              state
        );
    }

    @Auto
    public static void tick(World world, BlockPos pos, BlockState state, MudStoveBlockEntity blockEntity) {
        // Tick details...
    }

    public void somethingTest() {
        this.testInteger = 500;
        this.testString = "wtf";
    }
}
