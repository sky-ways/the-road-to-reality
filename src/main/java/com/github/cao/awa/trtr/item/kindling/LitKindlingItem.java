package com.github.cao.awa.trtr.item.kindling;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.dev.InventoryUtil;
import com.github.cao.awa.trtr.item.TrtrItem;
import com.github.cao.awa.trtr.item.TrtrItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

@Auto
public class LitKindlingItem extends TrtrItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:lit_kindling");
    @Auto
    public static final FabricItemSettings SETTINGS = new FabricItemSettings().maxCount(1);

    @Auto
    public LitKindlingItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        stack.decrement(1);
        entity.setOnFireFor(5);

        return ActionResult.SUCCESS;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();

        PlayerEntity user = context.getPlayer();

        ItemStack litKindlingStack = user.getStackInHand(context.getHand());

        BlockPos sourcePos = context.getBlockPos();
        BlockState sourceState = world.getBlockState(sourcePos);

        if (sourceState.getBlock() == Blocks.WATER_CAULDRON && sourceState.get(LeveledCauldronBlock.LEVEL) > 0) {
            extinguish(user,
                       world,
                       litKindlingStack
            );

            return ActionResult.SUCCESS;
        } else {
            BlockPos firePos = sourcePos.offset(context.getSide());

            if (AbstractFireBlock.canPlaceAt(world,
                                             firePos,
                                             context.getHorizontalPlayerFacing()
            )) {
                BlockState blockState2 = AbstractFireBlock.getState(world,
                                                                    firePos
                );
                world.setBlockState(firePos,
                                    blockState2,
                                    11
                );
                world.emitGameEvent(user,
                                    GameEvent.BLOCK_PLACE,
                                    sourcePos
                );
                ItemStack itemStack = context.getStack();
                if (user instanceof ServerPlayerEntity) {
                    Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity) user,
                                                  firePos,
                                                  itemStack
                    );
                    itemStack.decrement(1);
                }

                return ActionResult.success(world.isClient());
            } else {
                return ActionResult.FAIL;
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack litKindlingStack = user.getStackInHand(hand);

        BlockHitResult blockHitResult = raycast(world,
                                                user,
                                                RaycastContext.FluidHandling.SOURCE_ONLY
        );
        if (blockHitResult.getType() != HitResult.Type.MISS) {
            if (blockHitResult.getType() == HitResult.Type.BLOCK) {
                BlockPos blockPos = blockHitResult.getBlockPos();
                if (! world.canPlayerModifyAt(user,
                                              blockPos
                )) {
                    return TypedActionResult.pass(litKindlingStack);
                }

                if (world.getFluidState(blockPos)
                         .isIn(FluidTags.WATER)) {
                    extinguish(user,
                               world,
                               litKindlingStack
                    );

                    return TypedActionResult.consume(litKindlingStack);
                }
            }
        }
        return TypedActionResult.pass(litKindlingStack);
    }

    public void extinguish(PlayerEntity user, World world, ItemStack litKindlingStack) {
        ItemStack result = new ItemStack(TrtrItems.get(KindlingItem.class));
        InventoryUtil.insertOrDrop(user,
                                   world,
                                   result
        );

        litKindlingStack.decrement(1);
    }

    // TODO
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack,
                            world,
                            entity,
                            slot,
                            selected
        );
    }
}
