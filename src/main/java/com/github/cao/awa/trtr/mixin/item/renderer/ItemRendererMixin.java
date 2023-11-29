package com.github.cao.awa.trtr.mixin.item.renderer;

import net.minecraft.client.render.item.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
//    @Shadow
//    @Final
//    private ItemModels models;
//    @Shadow
//    @Final
//    private static ModelIdentifier TRIDENT;
//    @Shadow
//    @Final
//    private static ModelIdentifier SPYGLASS;
//
//    @Shadow
//    protected static boolean usesDynamicDisplay(ItemStack stack) {
//        return false;
//    }
//
//    @Shadow
//    public static VertexConsumer getDirectDynamicDisplayGlintConsumer(VertexConsumerProvider provider, RenderLayer layer, MatrixStack.Entry entry) {
//        return null;
//    }
//
//    @Shadow
//    public static VertexConsumer getDynamicDisplayGlintConsumer(VertexConsumerProvider provider, RenderLayer layer, MatrixStack.Entry entry) {
//        return null;
//    }
//
//    @Shadow
//    public static VertexConsumer getDirectItemGlintConsumer(VertexConsumerProvider provider, RenderLayer layer, boolean solid, boolean glint) {
//        return null;
//    }
//
//    @Shadow
//    public static VertexConsumer getItemGlintConsumer(VertexConsumerProvider vertexConsumers, RenderLayer layer, boolean solid, boolean glint) {
//        return null;
//    }
//
//    @Shadow
//    protected abstract void renderBakedItemModel(BakedModel model, ItemStack stack, int light, int overlay, MatrixStack matrices, VertexConsumer vertices);
//
//    @Shadow
//    @Final
//    private BuiltinModelItemRenderer builtinModelItemRenderer;
//
//    @Shadow
//    public abstract BakedModel getModel(ItemStack stack, @Nullable World world, @Nullable LivingEntity entity, int seed);
//
//    /**
//     * @author
//     * @reason
//     */
//    @Overwrite
//    public void renderItem(ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model) {
//        if (! stack.isEmpty()) {
//            matrices.push();
//            boolean bl = renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.GROUND || renderMode == ModelTransformationMode.FIXED;
//            if (bl) {
//                if (stack.isOf(Items.TRIDENT)) {
//                    model = this.models.getModelManager()
//                                       .getModel(TRIDENT);
//                } else if (stack.isOf(Items.SPYGLASS)) {
//                    model = this.models.getModelManager()
//                                       .getModel(SPYGLASS);
//                }
//            }
//
//            model.getTransformation()
//                 .getTransformation(renderMode)
//                 .apply(leftHanded,
//                        matrices
//                 );
//            matrices.translate(- 0.5F,
//                               - 0.5F,
//                               - 0.5F
//            );
//            if (! model.isBuiltin() && (! stack.isOf(Items.TRIDENT) || bl)) {
//                boolean bl2;
//                if (renderMode != ModelTransformationMode.GUI && ! renderMode.isFirstPerson() && stack.getItem() instanceof BlockItem) {
//                    Block block = ((BlockItem) stack.getItem()).getBlock();
//                    bl2 = ! (block instanceof TransparentBlock) && ! (block instanceof StainedGlassPaneBlock);
//                } else {
//                    bl2 = true;
//                }
//
//                RenderLayer renderLayer = RenderLayers.getItemLayer(stack,
//                                                                    bl2
//                );
//                VertexConsumer vertexConsumer;
//                if (usesDynamicDisplay(stack) && stack.hasGlint()) {
//                    matrices.push();
//                    MatrixStack.Entry entry = matrices.peek();
//                    if (renderMode == ModelTransformationMode.GUI) {
//                        MatrixUtil.scale(entry.getPositionMatrix(),
//                                         0.5F
//                        );
//                    } else if (renderMode.isFirstPerson()) {
//                        MatrixUtil.scale(entry.getPositionMatrix(),
//                                         0.75F
//                        );
//                    }
//
//                    if (bl2) {
//                        vertexConsumer = getDirectDynamicDisplayGlintConsumer(vertexConsumers,
//                                                                              renderLayer,
//                                                                              entry
//                        );
//                    } else {
//                        vertexConsumer = getDynamicDisplayGlintConsumer(vertexConsumers,
//                                                                        renderLayer,
//                                                                        entry
//                        );
//                    }
//
//                    matrices.pop();
//                } else if (bl2) {
//                    vertexConsumer = getDirectItemGlintConsumer(vertexConsumers,
//                                                                renderLayer,
//                                                                true,
//                                                                stack.hasGlint()
//                    );
//                } else {
//                    vertexConsumer = getItemGlintConsumer(vertexConsumers,
//                                                          renderLayer,
//                                                          true,
//                                                          stack.hasGlint()
//                    );
//                }
//
//                this.renderBakedItemModel(model,
//                                          stack,
//                                          light,
//                                          overlay,
//                                          matrices,
//                                          vertexConsumer
//                );
//
//                stack = new ItemStack(Items.STICK);
//                model = getModel(stack,
//                                 null,
//                                 null,
//                                 0
//                );
//
//                renderBakedItemModel(model,
//                                     stack,
//                                     light,
//                                     overlay,
//                                     matrices,
//                                     vertexConsumer
//                );
//            } else {
//                this.builtinModelItemRenderer.render(stack,
//                                                     renderMode,
//                                                     matrices,
//                                                     vertexConsumers,
//                                                     light,
//                                                     overlay
//                );
//            }
//
//            matrices.pop();
//        }
//    }
}
