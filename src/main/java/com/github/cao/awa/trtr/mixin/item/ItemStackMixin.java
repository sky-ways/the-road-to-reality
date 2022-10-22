package com.github.cao.awa.trtr.mixin.item;

import com.github.cao.awa.modmdo.security.*;
import com.github.cao.awa.trtr.database.properties.*;
import com.google.common.collect.*;
import com.mojang.brigadier.exceptions.*;
import com.mojang.logging.*;
import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.advancement.criterion.*;
import net.minecraft.block.*;
import net.minecraft.block.pattern.*;
import net.minecraft.client.item.*;
import net.minecraft.command.argument.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.decoration.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.*;
import net.minecraft.server.network.*;
import net.minecraft.sound.*;
import net.minecraft.stat.*;
import net.minecraft.tag.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;
import org.slf4j.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.text.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

}
