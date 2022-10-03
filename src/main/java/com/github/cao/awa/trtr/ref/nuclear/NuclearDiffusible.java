package com.github.cao.awa.trtr.ref.nuclear;

import com.github.cao.awa.trtr.math.*;
import com.github.cao.awa.trtr.type.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

import java.util.*;

import static com.github.cao.awa.trtr.TrtrMod.LOGGER;

public interface NuclearDiffusible {
    default Set<Entity> simple(@NotNull World world, BlockPos source) {
        ObjectOpenHashSet<Entity> entities = new ObjectOpenHashSet<>();

        entities.addAll(world.getOtherEntities(null, new Box(source.getX() - 5, source.getY() - 5, source.getZ() - 5, source.getX() + 5, source.getY() + 5, source.getZ() + 5)));

        return entities;
    }

    double radiation();

    double radioactivity();

    default Set<Entity> track(@NotNull World world, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        List<Entity> entities = world.getOtherEntities(null, new Box(x - 10, y - 10, z - 10, x + 10, y + 10, z + 10));
        Set<Entity> result = new ObjectOpenHashSet<>();

        for (Entity entity : entities) {
            Vec3d entityPos = entity.getPos();

            Vec3d current = new Vec3d(pos.getX(), pos.getY(), pos.getZ());

            int failed = 32;

            Box box = new Box(Math.floor(entityPos.getX()), Math.floor(entity.getY()), Math.floor(entityPos.getZ()), Math.floor(entityPos.getX()) + 1.1, entity.getEyeY() + 0.5, Math.floor(entityPos.getZ() + 1.1));

            LOGGER.debug("Track start");

            Vec3d last = null;

            double radiation = radiation();

            while (true) {
                current = abs(
                        current,
                        trackTo(entity, current.getX(), current.getY(), current.getZ()),
                        0,
                        0,
                        0.5
                );

                radiation -= TrtrBlockRadiationResistance.get(world.getBlockState(new BlockPos(current)));

                if (box.contains(current)) {
                    LOGGER.debug("Nuclear tracked: " + current);
                    break;
                } else {
                    LOGGER.debug("Nuclear tracking: " + current + " ? " + box);
                }

                if (failed-- == 0 || current.equals(last) || radiation < 0) {
                    LOGGER.debug("Track failed: " + current + " ? " + box);
                    break;
                }

                last = current;
            }
        }

        return result;
    }

    default Rotation<Float> trackTo(Entity targetEntity, double x, double y, double z) {
        double distanceX = targetEntity.getX() - x;
        double distanceZ = targetEntity.getZ() - z;
        double distanceY = targetEntity instanceof LivingEntity livingEntity ? livingEntity.getEyeY() - y : (targetEntity.getBoundingBox().minY + targetEntity.getBoundingBox().maxY) / 2.0D - y;

        float yaw = (float)(MathHelper.atan2(distanceZ, distanceX) * 57.2957763671875D) - 90.0F;
        float pitch = (float)(- MathHelper.atan2(distanceY, Math.sqrt(distanceX * distanceX + distanceZ * distanceZ)) * 57.2957763671875D);
        return new Rotation<>(pitch, yaw);
    }

    default Vec3d abs(Vec3d current, Rotation<Float> rotation, double offsetX, double offsetY, double offsetZ) {
        float f = MathHelper.cos((rotation.yaw() + 90.0F) * 0.017453292F);
        float g = MathHelper.sin((rotation.yaw() + 90.0F) * 0.017453292F);
        float h = MathHelper.cos(- rotation.pitch() * 0.017453292F);
        float j = MathHelper.cos((- rotation.pitch() + 90.0F) * 0.017453292F);
        Vec3d vec3d2 = new Vec3d(f * h, MathHelper.sin(- rotation.pitch() * 0.017453292F), g * h);
        Vec3d vec3d3 = new Vec3d(f * j, MathHelper.sin((- rotation.pitch() + 90.0F) * 0.017453292F), g * j);
        Vec3d vec3d4 = vec3d2.crossProduct(vec3d3).multiply(-1.0D);
        return new Vec3d(
                current.x + vec3d2.x * offsetZ + vec3d3.x * offsetY + vec3d4.x * offsetX,
                current.y + vec3d2.y * offsetZ + vec3d3.y * offsetY + vec3d4.y * offsetX,
                current.z + vec3d2.z * offsetZ + vec3d3.z * offsetY + vec3d4.z * offsetX
        );
    }
}
