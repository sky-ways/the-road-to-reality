package com.github.cao.awa.trtr.gas.database;

import com.github.cao.awa.apricot.mathematic.base.SkippedBase256;
import com.github.cao.awa.apricot.util.io.bytes.BytesUtil;
import com.github.cao.awa.trtr.TrtrMod;
import com.github.cao.awa.trtr.database.KeyValueBytesDatabase;
import com.github.cao.awa.trtr.database.KeyValueDatabase;
import com.github.cao.awa.trtr.gas.BlockGas;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Supplier;

public class GasDatabase extends KeyValueDatabase<BlockPos, BlockGas> {
    private final KeyValueBytesDatabase delegate;

    public GasDatabase(Supplier<Map<BlockPos, BlockGas>> cacheDelegate, KeyValueBytesDatabase delegate) {
        super(cacheDelegate);
        this.delegate = delegate;
    }

    @Override
    public void put(BlockPos key, BlockGas value) {
        cache().update(
                key,
                value,
                this :: update
        );
    }

    private void update(BlockPos key, BlockGas value) {
        NbtCompound compound = new NbtCompound();

        TrtrMod.NBT_SERIALIZE_FRAMEWORK.writeNbt(value,
                                                 compound
        );

        this.delegate.put(key(key),
                          compound.toString()
                                  .getBytes(StandardCharsets.UTF_8)
        );
    }

    @Override
    public BlockGas get(BlockPos key) {
        return cache().get(
                key,
                this :: query
        );
    }

    private BlockGas query(BlockPos key) {
        byte[] data = this.delegate.get(key(key));
        if (data == null) {
            return null;
        }

        String nbtString = new String(data,
                                      StandardCharsets.UTF_8
        );

        NbtCompound compound = TrtrMod.NBT_SERIALIZE_FRAMEWORK.createCompound(nbtString);

        if (compound == null) {
            return null;
        }

        BlockGas gas = new BlockGas();

        TrtrMod.NBT_SERIALIZE_FRAMEWORK.init(gas);

        TrtrMod.NBT_SERIALIZE_FRAMEWORK.readNbt(gas,
                                                compound
        );

        return gas;
    }

    @Override
    public void remove(BlockPos key) {
        cache().delete(
                key,
                this :: delete
        );
    }

    private void delete(BlockPos key) {
        this.delegate.remove(key(key));
    }

    public byte[] key(BlockPos pos) {
        return BytesUtil.concat(
                SkippedBase256.intToBuf(pos.getX()),
                SkippedBase256.intToBuf(pos.getY()),
                SkippedBase256.intToBuf(pos.getZ())
        );
    }
}
