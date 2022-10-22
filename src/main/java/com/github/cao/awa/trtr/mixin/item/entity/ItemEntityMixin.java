package com.github.cao.awa.trtr.mixin.item.entity;

import com.github.cao.awa.trtr.database.properties.*;
import com.github.cao.awa.trtr.dev.dump.mixin.item.entity.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.function.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.*;
import net.minecraft.entity.data.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.*;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity implements PropertiesAccessible {
    private static final Map<String, ThreeConsumer<World, BlockPos, ItemEntity>> actions = EntrustParser.operation(
            new Object2ObjectOpenHashMap<>(),
            map -> {
                map.put(
                        "inFire",
                        ItemEntityMixinDev::inFire
                );
                map.put(
                        "onFire",
                        ItemEntityMixinDev::onFire
                );
                map.put(
                        "inLava",
                        ItemEntityMixinDev::inLava
                );
            }
    );

    private final InstanceProperties properties = new InstanceProperties();

    public ItemEntityMixin(EntityType<?> type, World world) {
        super(
                type,
                world
        );
    }

    @Shadow
    public abstract ItemStack getStack();

    @Shadow @Final private static TrackedData<ItemStack> STACK;

    @Redirect(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ItemEntity;isInvulnerableTo(Lnet/minecraft/entity/damage/DamageSource;)Z"))
    public boolean firing(ItemEntity instance, DamageSource source) {
        if (actions.containsKey(source.getName())) {
            actions.get(source.getName())
                   .accept(instance.world,
                           instance.getBlockPos(),
                           instance
                   );
            return true;
        }
        return instance.isInvulnerableTo(source);
    }

    @Override
    public InstanceProperties getProperties() {
        return this.properties;
    }

    @Override
    public void setProperties(InstanceProperties properties) {
        this.properties.readProperties(properties);
    }
}
