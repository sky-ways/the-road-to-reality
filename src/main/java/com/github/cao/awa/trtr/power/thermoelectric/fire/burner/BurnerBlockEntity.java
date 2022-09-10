package com.github.cao.awa.trtr.power.thermoelectric.fire.burner;

import com.github.cao.awa.trtr.heat.conductor.*;
import com.github.cao.awa.trtr.properties.*;
import com.github.cao.awa.trtr.ref.block.*;
import com.github.cao.awa.trtr.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.operational.*;
import com.google.common.collect.*;
import net.minecraft.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.network.listener.*;
import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.screen.*;
import net.minecraft.tag.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.util.collection.*;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;
import net.minecraft.world.explosion.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class BurnerBlockEntity extends LootableContainerBlockEntity implements BlockEntityTicker<BurnerBlockEntity>, HeatConductive {
    private final static Map<Item, Integer> FUEL_TIME = AbstractFurnaceBlockEntity.createFuelTimeMap();
    private final static Map<Item, Integer> HEAT = createFuelHeatMap();
    private final BlockEntityProperties<BurnerBlockEntity> properties = new BlockEntityProperties<>(this);
    private final Random random = new Random();
    private Identifier lastBurning;
    private DefaultedList<ItemStack> inventory;
    private final BurnerBlockHeatConductor conductor;

    public BurnerBlockEntity(BlockPos pos, BlockState state) {
        super(TrtrBlockEntityType.BURNER, pos, state);
        this.inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
        this.conductor = new BurnerBlockHeatConductor();
    }

    public static Map<Item, Integer> createFuelHeatMap() {
        Map<Item, Integer> map = Maps.newLinkedHashMap();
        addHeat(map, Items.LAVA_BUCKET, 1100);
        addHeat(map, Blocks.COAL_BLOCK, 1300);
        addHeat(map, Items.BLAZE_ROD, 1500);
        addHeat(map, Items.COAL, 1200);
        addHeat(map, Items.CHARCOAL, 1200);
        addHeat(map, ItemTags.LOGS, 1100);
        addHeat(map, ItemTags.PLANKS, 700);
        addHeat(map, ItemTags.WOODEN_STAIRS, 700);
        addHeat(map, ItemTags.WOODEN_SLABS, 700);
        addHeat(map, ItemTags.WOODEN_TRAPDOORS, 700);
        addHeat(map, ItemTags.WOODEN_PRESSURE_PLATES, 700);
        addHeat(map, Blocks.OAK_FENCE, 700);
        addHeat(map, Blocks.BIRCH_FENCE, 700);
        addHeat(map, Blocks.SPRUCE_FENCE, 700);
        addHeat(map, Blocks.JUNGLE_FENCE, 700);
        addHeat(map, Blocks.DARK_OAK_FENCE, 700);
        addHeat(map, Blocks.ACACIA_FENCE, 700);
        addHeat(map, Blocks.MANGROVE_FENCE, 700);
        addHeat(map, Blocks.OAK_FENCE_GATE, 700);
        addHeat(map, Blocks.BIRCH_FENCE_GATE, 700);
        addHeat(map, Blocks.SPRUCE_FENCE_GATE, 700);
        addHeat(map, Blocks.JUNGLE_FENCE_GATE, 700);
        addHeat(map, Blocks.DARK_OAK_FENCE_GATE, 700);
        addHeat(map, Blocks.ACACIA_FENCE_GATE, 700);
        addHeat(map, Blocks.MANGROVE_FENCE_GATE, 700);
        addHeat(map, Blocks.NOTE_BLOCK, 700);
        addHeat(map, Blocks.BOOKSHELF, 700);
        addHeat(map, Blocks.LECTERN, 700);
        addHeat(map, Blocks.JUKEBOX, 700);
        addHeat(map, Blocks.CHEST, 700);
        addHeat(map, Blocks.TRAPPED_CHEST, 700);
        addHeat(map, Blocks.CRAFTING_TABLE, 700);
        addHeat(map, Blocks.DAYLIGHT_DETECTOR, 600);
        addHeat(map, ItemTags.BANNERS, 700);
        addHeat(map, Items.BOW, 700);
        addHeat(map, Items.FISHING_ROD, 700);
        addHeat(map, Blocks.LADDER, 700);
        addHeat(map, ItemTags.SIGNS, 700);
        addHeat(map, Items.WOODEN_SHOVEL, 700);
        addHeat(map, Items.WOODEN_SWORD, 700);
        addHeat(map, Items.WOODEN_HOE, 700);
        addHeat(map, Items.WOODEN_AXE, 700);
        addHeat(map, Items.WOODEN_PICKAXE, 700);
        addHeat(map, ItemTags.WOODEN_DOORS, 700);
        addHeat(map, ItemTags.BOATS, 700);
        addHeat(map, ItemTags.WOOL, 650);
        addHeat(map, ItemTags.WOODEN_BUTTONS, 700);
        addHeat(map, Items.STICK, 700);
        addHeat(map, ItemTags.SAPLINGS, 600);
        addHeat(map, Items.BOWL, 700);
        addHeat(map, ItemTags.WOOL_CARPETS, 650);
        addHeat(map, Blocks.DRIED_KELP_BLOCK, 650);
        addHeat(map, Items.CROSSBOW, 700);
        addHeat(map, Blocks.BAMBOO, 700);
        addHeat(map, Blocks.DEAD_BUSH, 700);
        addHeat(map, Blocks.SCAFFOLDING, 700);
        addHeat(map, Blocks.LOOM, 700);
        addHeat(map, Blocks.BARREL, 700);
        addHeat(map, Blocks.CARTOGRAPHY_TABLE, 700);
        addHeat(map, Blocks.FLETCHING_TABLE, 700);
        addHeat(map, Blocks.SMITHING_TABLE, 700);
        addHeat(map, Blocks.COMPOSTER, 700);
        addHeat(map, Blocks.AZALEA, 600);
        addHeat(map, Blocks.FLOWERING_AZALEA, 600);
        addHeat(map, Blocks.MANGROVE_ROOTS, 600);
        return map;
    }

    private static void addHeat(Map<Item, Integer> fuelHeats, TagKey<Item> tag, int fuelHeat) {
        for (RegistryEntry<Item> itemRegistryEntry : Registry.ITEM.iterateEntries(tag)) {
            if (! isNonFlammableWood(itemRegistryEntry.value())) {
                fuelHeats.put(itemRegistryEntry.value(), fuelHeat);
            }
        }
    }

    private static void addHeat(Map<Item, Integer> fuelHeats, ItemConvertible item, int fuelHeat) {
        Item item2 = item.asItem();
        if (isNonFlammableWood(item2)) {
            if (SharedConstants.isDevelopment) {
                throw Util.throwOrPause(new IllegalStateException("A developer tried to explicitly make fire resistant item " + item2.getName(null).getString() + " a furnace fuel. That will not work!"));
            }
        } else {
            fuelHeats.put(item2, fuelHeat);
        }
    }

    private static boolean isNonFlammableWood(Item item) {
        return item.getRegistryEntry().isIn(ItemTags.NON_FLAMMABLE_WOOD);
    }

    public Integer getFuelTime(Item item) {
        return FUEL_TIME.getOrDefault(item, 0);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        properties.putFloat("burned", nbt.getInt("burned"));
        properties.putInt("burningTime", nbt.getInt("burningTime"));
        this.lastBurning = new Identifier(nbt.getString("burningBlock"));
        this.conductor.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putDouble("burned", properties.getDoubleOrDefault("burned", 0));
        nbt.putLong("burningTime", properties.getIntOrDefault("burningTime", 0));
        nbt.putFloat("seal", properties.getFloatOrDefault("seal", 1));
        this.conductor.writeNbt(nbt);
        if (lastBurning != null) {
            nbt.putString("burningBlock", lastBurning.toString());
        }
        Inventories.writeNbt(nbt, inventory);
    }

    @Override
    protected Text getContainerName() {
        return Text.of("Burner");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new BurnerScreenHandler(syncId, playerInventory, this, properties);
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, BurnerBlockEntity blockEntity) {
        if (burn()) {
            if (! state.get(BurnerBlock.BURNING)) {
                world.setBlockState(pos, state.with(BurnerBlock.BURNING, true), 2);
            }
            conductor.heat(new ImmutableConductor(properties.getIntOrDefault("heat", 0)));
            conductor.endothermic(world, pos, state);
            tickBurning(world, pos, state);
        } else {
            if (state.get(BurnerBlock.BURNING)) {
                world.setBlockState(pos, state.with(BurnerBlock.BURNING, false), 2);
            }
        }
        markDirty();
    }

    public void tickBurning(World world, BlockPos pos, BlockState state) {
        BlockPos targetPos = pos.offset(Direction.UP);
        BlockState targetState = world.getBlockState(targetPos);
        tickFire(world, pos, state);
        if (targetState.getMaterial().isBurnable()) {
            properties.updateDouble("burned", source -> {
                int heat = conductor.getTemperature();
                return source + properties.getFloatOrDefault("seal", 1) * (heat / 750);
            });
            Identifier last = Registry.BLOCK.getId(targetState.getBlock());
            if (last.equals(lastBurning)) {
                double burned = properties.getDoubleOrDefault("burned", 1D);
                if (burned > 199) {
                    world.setBlockState(targetPos, Blocks.COAL_BLOCK.getDefaultState(), 3);
                    properties.putFloat("burned", 0);
                }
            } else {
                properties.putFloat("burned", 0);
                lastBurning = last;
            }
        }
    }

    public void tickFire(World world, BlockPos pos, BlockState state) {
        float seal = 0;
        BlockPos sourcePos = pos;
        pos = pos.up();
        BlockState targetState = world.getBlockState(pos);
        if (! state.getMaterial().isBurnable()) {
            if (state.isAir()) {
                clearFire(world, pos, state);
            }
            return;
        }
        boolean doFire = true;
        for (Direction direction : Direction.values()) {
            if (direction == Direction.DOWN || direction == Direction.UP) {
                continue;
            }
            BlockPos offset = pos.offset(direction);
            if (world.getBlockState(offset).isAir()) {
                if (doFire) {
                    if (random.nextInt(300) == 299) {
                        if (world.canSetBlock(offset)) {
                            world.setBlockState(offset, AbstractFireBlock.getState(world, offset));
                        }
                        doFire = false;
                    }
                }
                continue;
            }
            seal += 0.2;
        }
        properties.putFloat("seal", seal);
    }

    public void clearFire(World world, BlockPos pos, BlockState state) {
        pos = pos.up();
        for (Direction direction : Direction.values()) {
            if (direction == Direction.DOWN || direction == Direction.UP) {
                continue;
            }
            BlockPos offset = pos.offset(direction);
            if (world.getBlockState(offset).isOf(Blocks.FIRE)) {
                if (world.canSetBlock(offset)) {
                    world.setBlockState(offset, Blocks.AIR.getDefaultState());
                }
            }
        }
    }

    public boolean burn() {
        ItemStack stack = inventory.get(0);
        int stackCount = stack.getCount();
        if (stack.getItem() instanceof BlockItem blockItem) {
            if (blockItem.getBlock() == Blocks.TNT) {
                if (world != null && ! world.isClient) {
                    stack.setCount(0);
                    this.world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), Math.min((stackCount > 1 ? 0.6F : 1F) * stackCount, 16), Explosion.DestructionType.BREAK);
                }
            }
        }
        if (isBurning()) {
            properties.updateInt("burningTime", source -> source - 1);
            return true;
        }
        if (stack.isEmpty()) {
            return false;
        }
        int time = FUEL_TIME.getOrDefault(stack.getItem(), 0);
        if (time > 0) {
            properties.putInt("burningTime", time);
            properties.putInt("heat", HEAT.getOrDefault(stack.getItem(), 0));
            properties.putInt("fuelTime", time);
            stack.setCount(stackCount - 1);
            return true;
        }
        return false;
    }

    public boolean isBurning() {
        return properties.getIntOrDefault("burningTime", 0) > 0;
    }

    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (isBurning()) {
            boolean willFire = properties.getFloatOrDefault("heat", 0F) > 599;
            OperationalFloat offset = new OperationalFloat();
            if (entity instanceof PlayerEntity player) {
                if (player.isCreative() || player.isSpectator()) {
                    return;
                }
                ItemStack stack = player.getInventory().getArmorStack(0);
                willFire = willFire && stack.isEmpty() || stack.isOf(Items.LEATHER_BOOTS);
                if (! willFire) {
                    if (stack.isOf(Items.IRON_BOOTS)) {
                        offset.set(0.001F);
                    } else if (stack.isOf(Items.DIAMOND_BOOTS)) {
                        offset.set(- 0.003F);
                    } else if (stack.isOf(Items.GOLDEN_BOOTS)) {
                        offset.set(- 0.001F);
                    }
                }
            }
            if (willFire) {
                entity.setOnFireFor(1);
            }
            entity.damage(new DamageSource("eper.burner.hot"), properties.calculateFloat("heat",
                    heat -> heat > 0F, // Is heat higher than zero
                    heat -> heat * (0.005F - offset.get()), // Calculate damage
                    0 // Else no damage
            ));
        }
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    public void tickClient(World world, BlockPos pos, BlockState state, BurnerBlockEntity burnerBlockEntity) {
        if (state.get(BurnerBlock.BURNING) && world.getBlockState(pos.up()).isAir()) {
            CampfireBlock.spawnSmokeParticle(world, pos, true, false);
        }
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return inventory;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.inventory = list;
    }

    @Override
    public HeatConductor getConductor() {
        return conductor;
    }

    @Override
    public int thermalConductivity() {
        return 100;
    }
}
