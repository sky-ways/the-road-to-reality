package com.github.cao.awa.trtr.mixin.command.data;

import com.github.cao.awa.trtr.database.properties.*;
import com.mojang.brigadier.exceptions.*;
import net.minecraft.block.entity.*;
import net.minecraft.command.*;
import net.minecraft.nbt.*;
import net.minecraft.server.command.*;
import net.minecraft.text.*;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(DataCommand.class)
public class DataCommandMixin {
    @Inject(method = "executeGet(Lnet/minecraft/server/command/ServerCommandSource;Lnet/minecraft/command/DataCommandObject;)I", at = @At("RETURN"))
    private static void getProperties(ServerCommandSource source, DataCommandObject object, CallbackInfoReturnable<Integer> cir) throws CommandSyntaxException {
        NbtCompound nbt = object.getNbt();
        BlockEntity entity = source.getWorld().getBlockEntity(new BlockPos(nbt.getInt("x"), nbt.getInt("y"), nbt.getInt("z")));
        if (entity instanceof PropertiesAccessible accessible) {
            source.sendFeedback(Text.literal("properties: " + accessible.getProperties().toNbtCompound().toString()), false);
        }
    }
}
