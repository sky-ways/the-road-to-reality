package com.github.cao.awa.trtr.ref.nuclear;

import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

import java.util.*;

public interface NuclearDiffusible {
    default Set<Entity> exposed(@NotNull World world, BlockPos source) {
        int staExposed = 7;
        int exposed = staExposed;
        int alx = 0;
        int alz;
        int aly;
        int exz;
        int exy;

        ObjectOpenHashSet<Entity> entities = new ObjectOpenHashSet<>();

        while (exposed > 0) {
            int x = source.getX();
            int y = source.getY();
            int z = source.getZ();
            BlockPos px = new BlockPos(x + alx, y, z);
            BlockPos pdx = new BlockPos(x - alx, y, z);
            alz = 0;
            exz = staExposed - alx;
            for (int iz = Math.min(exz, staExposed); iz > 0; iz--) {
                BlockPos pxz = new BlockPos(px.getX(), px.getY(), z - alz);
                BlockPos pxdz = new BlockPos(px.getX(), px.getY(), z + alz);
                BlockPos pdxz = new BlockPos(pdx.getX(), pdx.getY(), z - alz);
                BlockPos pdxdz = new BlockPos(pdx.getX(), pdx.getY(), z + alz);

                entities.addAll(world.getOtherEntities(null, new Box(pxz)));
                entities.addAll(world.getOtherEntities(null, new Box(pxdz)));
                entities.addAll(world.getOtherEntities(null, new Box(pdxz)));
                entities.addAll(world.getOtherEntities(null, new Box(pdxdz)));

                aly = 0;
                exy = Math.min(exz, staExposed - alz);
                for (int iy = Math.min(exy, staExposed); iy > 0; iy--) {
                    BlockPos pxzy = new BlockPos(pxz.getX(), y - aly, pxz.getZ());
                    BlockPos pxzdy = new BlockPos(pxz.getX(), y + aly, pxz.getZ());
                    BlockPos pxdzdy = new BlockPos(pxdz.getX(), y + aly, pxdz.getZ());
                    BlockPos pdxdzdy = new BlockPos(pdxdz.getX(), y + aly, pdxdz.getZ());
                    BlockPos pdxzy = new BlockPos(pdxz.getX(), y - aly, pdxz.getZ());
                    BlockPos pxdzy = new BlockPos(pxdz.getX(), y - aly, pxdz.getZ());
                    BlockPos pdxdzy = new BlockPos(pdxdz.getX(), y - aly, pdxdz.getZ());
                    BlockPos pdxzdy = new BlockPos(pdxz.getX(), y + aly, pdxz.getZ());

                    entities.addAll(world.getOtherEntities(null, new Box(pxzy)));
                    entities.addAll(world.getOtherEntities(null, new Box(pxzdy)));
                    entities.addAll(world.getOtherEntities(null, new Box(pxdzy)));
                    entities.addAll(world.getOtherEntities(null, new Box(pxdzdy)));
                    entities.addAll(world.getOtherEntities(null, new Box(pdxdzdy)));
                    entities.addAll(world.getOtherEntities(null, new Box(pdxdzy)));
                    entities.addAll(world.getOtherEntities(null, new Box(pdxzy)));
                    entities.addAll(world.getOtherEntities(null, new Box(pdxzdy)));

                    aly++;
                }

                alz++;
            }

            alx++;
            exposed--;
        }

        return entities;
    }

    default Set<Entity> simple(@NotNull World world, BlockPos source) {
        ObjectOpenHashSet<Entity> entities = new ObjectOpenHashSet<>();

        entities.addAll(world.getOtherEntities(null, new Box(source.getX() - 5, source.getY() - 5, source.getZ() - 5, source.getX() + 5, source.getY() + 5, source.getZ() + 5)));

        return entities;
    }
}
