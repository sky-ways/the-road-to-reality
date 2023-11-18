package com.github.cao.awa.trtr.renderer.block;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;

import java.util.function.Function;

public interface BlockRendererProvider<T extends BlockEntity> {
    Function<BlockEntityRendererFactory.Context, BlockEntityRenderer<T>> renderer();
}
