package com.github.cao.awa.trtr.mixin.client;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.network.SequencedPacketCreator;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(ClientPlayerInteractionManager.class)
public abstract class ClientPlayerInteractionManagerMixin {
//    @Inject(method = "updateBlockBreakingProgress", at = @At("HEAD"))
//    private static void updateBlockBreakingProgress(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
//
//    }

    @Shadow
    protected abstract void syncSelectedSlot();

    @Shadow
    private int blockBreakingCooldown;

    @Shadow
    private GameMode gameMode;

    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    protected abstract void sendSequencedPacket(ClientWorld world, SequencedPacketCreator packetCreator);

    @Shadow
    public abstract boolean breakBlock(BlockPos pos);

    @Shadow
    protected abstract boolean isCurrentlyBreaking(BlockPos pos);

    @Shadow
    private boolean breakingBlock;

//    @Shadow private float currentBreakingProgress;

    @Shadow
    private float blockBreakingSoundCooldown;

    @Shadow
    private BlockPos currentBreakingPos;

    @Shadow
    @Final
    private ClientPlayNetworkHandler networkHandler;
    @Shadow
    private ItemStack selectedStack;

    @Shadow
    public abstract boolean attackBlock(BlockPos pos, Direction direction);

    private final Map<BlockPos, Float> progressMap = ApricotCollectionFactor.newHashMap();
//
//    /**
//     * @author
//     * @reason
//     */
//    @Inject(method = "updateBlockBreakingProgress", at = @At("HEAD"), cancellable = true)
//    public void updateBlockBreakingProgress(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
//        this.syncSelectedSlot();
//        if (this.blockBreakingCooldown > 0) {
//            --this.blockBreakingCooldown;
//            cir.setReturnValue(true);
//        } else {
//            BlockState blockState;
//            if (this.gameMode.isCreative() && this.client.world.getWorldBorder().contains(pos)) {
//                this.blockBreakingCooldown = 5;
//                blockState = this.client.world.getBlockState(pos);
//                this.client.getTutorialManager().onBlockBreaking(this.client.world, pos, blockState, 1.0F);
//                this.sendSequencedPacket(this.client.world, (sequence) -> {
//                    this.breakBlock(pos);
//                    return new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.START_DESTROY_BLOCK, pos, direction, sequence);
//                });
//                cir.setReturnValue(true);
//            } else if (this.isCurrentlyBreaking(pos)) {
//                blockState = this.client.world.getBlockState(pos);
//                if (blockState.isAir()) {
//                    this.breakingBlock = false;
//                    cir.setReturnValue(false);
//                } else {
////                    this.currentBreakingProgress += blockState.calcBlockBreakingDelta(this.client.player, this.client.player.world, pos);
//                    final float progressDelta = blockState.calcBlockBreakingDelta(this.client.player, this.client.player.world, pos);
//                    this.progressMap.compute(pos, (k, v) -> v == null ? progressDelta : v + progressDelta);
//                    final float progress = this.progressMap.get(pos);
//
//                    if (this.blockBreakingSoundCooldown % 4.0F == 0.0F) {
//                        BlockSoundGroup blockSoundGroup = blockState.getSoundGroup();
//                        this.client.getSoundManager().play(new PositionedSoundInstance(blockSoundGroup.getHitSound(), SoundCategory.BLOCKS, (blockSoundGroup.getVolume() + 1.0F) / 8.0F, blockSoundGroup.getPitch() * 0.5F, SoundInstance.createRandom(), pos));
//                    }
//
//                    ++this.blockBreakingSoundCooldown;
//                    this.client.getTutorialManager().onBlockBreaking(this.client.world, pos, blockState, MathHelper.clamp(progress, 0.0F, 1.0F));
//                    if (progress >= 1.0F) {
//                        this.breakingBlock = false;
//                        this.sendSequencedPacket(this.client.world, (sequence) -> {
//                            this.breakBlock(pos);
//                            return new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK, pos, direction, sequence);
//                        });
//                        this.progressMap.put(pos, 0.0F);
//                        this.blockBreakingSoundCooldown = 0.0F;
//                        this.blockBreakingCooldown = 5;
//                    }
//
//                    this.client.world.setBlockBreakingInfo(this.client.player.getId(), this.currentBreakingPos, (int)(progress * 10.0F) - 1);
//                    cir.setReturnValue(true);
//                }
//            } else {
//                cir.setReturnValue(this.attackBlock(pos, direction));
//            }
//        }
//
//        cir.cancel();
//    }
//
//    @Inject(method = "attackBlock", at = @At("HEAD"), cancellable = true)
//    public void attackBlock(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
//        if (this.client.player.isBlockBreakingRestricted(this.client.world, pos, this.gameMode)) {
//            cir.setReturnValue(false);
//        } else if (!this.client.world.getWorldBorder().contains(pos)) {
//            cir.setReturnValue(false);
//        } else {
//            BlockState blockState;
//            if (this.gameMode.isCreative()) {
//                blockState = this.client.world.getBlockState(pos);
//                this.client.getTutorialManager().onBlockBreaking(this.client.world, pos, blockState, 1.0F);
//                this.sendSequencedPacket(this.client.world, (sequence) -> {
//                    this.breakBlock(pos);
//                    return new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.START_DESTROY_BLOCK, pos, direction, sequence);
//                });
//                this.blockBreakingCooldown = 5;
//            } else if (!this.breakingBlock || !this.isCurrentlyBreaking(pos)) {
//                if (this.breakingBlock) {
//                    this.networkHandler.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.ABORT_DESTROY_BLOCK, this.currentBreakingPos, direction));
//                }
//
//                blockState = this.client.world.getBlockState(pos);
//                this.client.getTutorialManager().onBlockBreaking(this.client.world, pos, blockState, 0.0F);
//                this.sendSequencedPacket(this.client.world, (sequence) -> {
//                    boolean bl = !blockState.isAir();
//                    if (bl && this.progressMap.getOrDefault(pos, 0F) == 0.0F) {
//                        blockState.onBlockBreakStart(this.client.world, pos, this.client.player);
//                    }
//
//                    if (bl && blockState.calcBlockBreakingDelta(this.client.player, this.client.player.world, pos) >= 1.0F) {
//                        this.breakBlock(pos);
//                        this.progressMap.put(pos, 0.0F);
//                    } else {
//                        this.breakingBlock = true;
//                        this.currentBreakingPos = pos;
//                        this.selectedStack = this.client.player.getMainHandStack();
//                        this.blockBreakingSoundCooldown = 0.0F;
//                        this.client.world.setBlockBreakingInfo(this.client.player.getId(), this.currentBreakingPos, (int)(this.progressMap.getOrDefault(pos, 0F) * 10.0F) - 1);
//                    }
//
//                    return new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.START_DESTROY_BLOCK, pos, direction, sequence);
//                });
//            }
//
//            cir.setReturnValue(true);
//        }
//
//        cir.cancel();
//    }
//
//    /**
//     * @author
//     * @reason
//     */
//    @Overwrite
//    public void cancelBlockBreaking() {
//        if (this.breakingBlock) {
//            BlockState blockState = this.client.world.getBlockState(this.currentBreakingPos);
//            this.client.getTutorialManager().onBlockBreaking(this.client.world, this.currentBreakingPos, blockState, -1.0F);
//            this.networkHandler.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.ABORT_DESTROY_BLOCK, this.currentBreakingPos, Direction.DOWN));
//            this.breakingBlock = false;
////            this.currentBreakingProgress = 0.0F;
////            this.client.world.setBlockBreakingInfo(this.client.player.getId(), this.currentBreakingPos, -1);
//            this.client.player.resetLastAttackedTicks();
//        }
//
//    }
}
