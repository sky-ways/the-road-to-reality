package com.github.cao.awa.trtr.mixin.nbt;

import net.minecraft.nbt.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;

import java.util.*;

@Mixin(NbtCompound.class)
public interface NbtCompoundInterface {
    @Invoker("toMap")
    Map<String, NbtElement> toMap();
}
