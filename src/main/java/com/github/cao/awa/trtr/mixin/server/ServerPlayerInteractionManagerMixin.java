package com.github.cao.awa.trtr.mixin.server;

import net.minecraft.server.network.ServerPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerPlayerInteractionManager.class)
public abstract class ServerPlayerInteractionManagerMixin {
//    @Shadow @Final protected ServerPlayerEntity player;
//
//    @Shadow protected abstract void method_41250(BlockPos pos, boolean success, int sequence, String reason);
//
//    @Shadow protected ServerWorld world;
//
//    @Shadow private GameMode gameMode;
//
//    @Shadow public abstract boolean isCreative();
//
//    @Shadow public abstract void finishMining(BlockPos pos, int sequence, String reason);
//
//    @Shadow private int startMiningTime;
//
//    @Shadow private int tickCounter;
//
//    @Shadow private BlockPos miningPos;
//
//    @Shadow private boolean mining;
//
//    @Shadow private int blockBreakingProgress;
//
//    @Shadow private boolean failedToMine;
//
//    @Shadow private BlockPos failedMiningPos;
//
//    @Shadow private int failedStartMiningTime;
//
//    @Shadow @Final private static Logger LOGGER;
//
//    private static final Map<BlockPos, Float> multilyMap = ApricotCollectionFactor.newHashMap();
//    private static final Map<BlockPos, Integer> breakingMap = ApricotCollectionFactor.newHashMap();
//
//    @Inject(method = "processBlockBreakingAction", at = @At("HEAD"), cancellable = true)
//    public void processBlockBreakingAction(BlockPos pos, PlayerActionC2SPacket.Action action, Direction direction, int worldHeight, int sequence, CallbackInfo ci) {
//        if (this.player.getEyePos().squaredDistanceTo(Vec3d.ofCenter(pos)) > ServerPlayNetworkHandler.MAX_BREAK_SQUARED_DISTANCE) {
//            this.method_41250(pos, false, sequence, "too far");
//            return;
//        }
//        if (pos.getY() >= worldHeight) {
//            this.player.networkHandler.sendPacket(new BlockUpdateS2CPacket(pos, this.world.getBlockState(pos)));
//            this.method_41250(pos, false, sequence, "too high");
//            return;
//        }
//        if (action == PlayerActionC2SPacket.Action.START_DESTROY_BLOCK) {
//            if (!this.world.canPlayerModifyAt(this.player, pos)) {
//                this.player.networkHandler.sendPacket(new BlockUpdateS2CPacket(pos, this.world.getBlockState(pos)));
//                this.method_41250(pos, false, sequence, "may not interact");
//                return;
//            }
//            if (this.isCreative()) {
//                this.finishMining(pos, sequence, "creative destroy");
//                return;
//            }
//            if (this.player.isBlockBreakingRestricted(this.world, pos, this.gameMode)) {
//                this.player.networkHandler.sendPacket(new BlockUpdateS2CPacket(pos, this.world.getBlockState(pos)));
//                this.method_41250(pos, false, sequence, "block action restricted");
//                return;
//            }
//            this.startMiningTime = this.tickCounter;
//            float f = 1.0f;
//            BlockState blockState = this.world.getBlockState(pos);
//            if (!blockState.isAir()) {
//                blockState.onBlockBreakStart(this.world, pos, this.player);
//                f = blockState.calcBlockBreakingDelta(this.player, this.player.world, pos);
//            }
//            if (!blockState.isAir() && f >= 1.0f) {
//                this.finishMining(pos, sequence, "insta mine");
//            } else {
//                if (this.mining) {
//                    this.player.networkHandler.sendPacket(new BlockUpdateS2CPacket(this.miningPos, this.world.getBlockState(this.miningPos)));
//                    this.method_41250(pos, false, sequence, "abort destroying since another started (client insta mine, server disagreed)");
//                }
//                this.mining = true;
//                this.miningPos = pos.toImmutable();
//                int i = (int)(f * 10.0f);
//                multilyMap.put(pos, f);
//                this.world.setBlockBreakingInfo(this.player.getId(), pos,
//                                                (int) submit(pos)
//                );
//                this.method_41250(pos, true, sequence, "actual start of destroying");
////                this.blockBreakingProgress = i;
//            }
//        } else if (action == PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK) {
//            if (pos.equals(this.miningPos)) {
////                int j = this.tickCounter - this.startMiningTime;
//                BlockState blockState = this.world.getBlockState(pos);
//                if (!blockState.isAir()) {
//                    float g = submit(pos);
//                    if (g >= 1.0) {
//                        this.mining = false;
//                        this.world.setBlockBreakingInfo(this.player.getId(), pos, -1);
//                        this.finishMining(pos, sequence, "destroyed");
//                        return;
//                    }
//                }
//            }
//            this.method_41250(pos, true, sequence, "stopped destroying");
//        } else if (action == PlayerActionC2SPacket.Action.ABORT_DESTROY_BLOCK) {
//            this.mining = false;
//            if (! Objects.equals(this.miningPos, pos)) {
//                LOGGER.warn("Mismatch in destroy block pos: {} {}",
//                            this.miningPos,
//                            pos
//                );
////                this.method_41250(pos, true, sequence, "aborted mismatched destroying");
//            }
//            float progress = submit(pos);
//            if (progress >= 1.0F) {
//                if (pos.equals(this.miningPos)) {
////                int j = this.tickCounter - this.startMiningTime;
//                    BlockState blockState = this.world.getBlockState(pos);
//                    if (!blockState.isAir()) {
//                        float g = submit(pos);
//                        if (g >= 1.0) {
//                            this.mining = false;
//                            this.world.setBlockBreakingInfo(this.player.getId(), pos, -1);
//                            this.finishMining(pos, sequence, "destroyed");
//                            return;
//                        }
//                    }
//                }
//            }else {
//                this.world.setBlockBreakingInfo(this.player.getId(),
//                                                pos,
//                                                (int) (progress * 10)
//                );
////            this.method_41250(pos, true, sequence, "aborted destroying");
//            }
//        }
//
//        ci.cancel();
//    }
//
//    private float submit(BlockPos pos) {
//        int ticks = this.tickCounter - this.startMiningTime;
//        breakingMap.compute(pos, (k, v) -> v == null ? ticks : ticks + v);
//        float done = multilyMap.get(pos) * breakingMap.get(pos);
//        return done;
//    }
}
