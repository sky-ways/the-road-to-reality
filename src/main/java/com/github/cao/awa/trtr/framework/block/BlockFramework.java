package com.github.cao.awa.trtr.framework.block;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.TrtrMod;
import com.github.cao.awa.trtr.annotation.property.AutoProperty;
import com.github.cao.awa.trtr.block.TrtrBlocks;
import com.github.cao.awa.trtr.block.item.TrtrBlockItems;
import com.github.cao.awa.trtr.block.stove.mud.MudStoveBlockEntity;
import com.github.cao.awa.trtr.framework.accessor.block.entity.BlockEntityAccessor;
import com.github.cao.awa.trtr.framework.accessor.block.entity.TrtrBlockEntityFactory;
import com.github.cao.awa.trtr.framework.accessor.block.entity.render.BlockEntityRenderAccessor;
import com.github.cao.awa.trtr.framework.accessor.block.item.BlockItemAccessor;
import com.github.cao.awa.trtr.framework.accessor.block.setting.BlockSettingAccessor;
import com.github.cao.awa.trtr.framework.accessor.identifier.IdentifierAccessor;
import com.github.cao.awa.trtr.framework.accessor.item.ItemSettingAccessor;
import com.github.cao.awa.trtr.framework.block.data.gen.BlockDataGenFramework;
import com.github.cao.awa.trtr.framework.exception.InvertOfControlException;
import com.github.cao.awa.trtr.framework.exception.NotStaticFieldException;
import com.github.cao.awa.trtr.framework.nbt.NbtSerializeFramework;
import com.github.cao.awa.trtr.framework.reflection.ReflectionFramework;
import com.github.cao.awa.trtr.item.TrtrItems;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.ExceptionEnvironment;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.receptacle.Receptacle;
import com.mojang.datafixers.types.Type;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BlockFramework extends ReflectionFramework {
    private static final Logger LOGGER = LogManager.getLogger("Trtr/BlockFramework");
    private final List<Block> blocks = ApricotCollectionFactor.newArrayList();
    private final Map<Class<? extends Block>, BlockEntityType<?>> blockEntities = ApricotCollectionFactor.newHashMap();
    private final BlockDataGenFramework DATA_GEN = new BlockDataGenFramework(this);
    private final NbtSerializeFramework nbtSerializeFramework = new NbtSerializeFramework(this);

    public void work() {
        // Working stream...
        reflection().getTypesAnnotatedWith(Auto.class)
                    .stream()
                    .filter(this :: match)
                    .map(this :: cast)
                    .filter(this :: verify)
                    .map(this :: block)
                    .forEach(this :: build);
    }

    public NbtSerializeFramework nbtSerializeFramework() {
        return this.nbtSerializeFramework;
    }

    private boolean match(Class<?> clazz) {
        // The abstract class cannot be instanced, filter it out.
        // And framework can process only Block, if not then reject the process in this framework.
        return ! Modifier.isAbstract(clazz.getModifiers()) && Block.class.isAssignableFrom(clazz) && dev(clazz);
    }

    private Class<Block> cast(Class<?> clazz) {
        // This cast should not move to map calls in working stream, EntrustEnvironment did not know what type you want!
        return EntrustEnvironment.cast(clazz);
    }

    private Block block(Class<Block> clazz) {
        // Construct the block using settings.
        // Here have not a default template settings for coping with settings missing.
        LOGGER.info("Constructing block: '{}'",
                    clazz.getName()
        );
        return EntrustEnvironment.trys(() -> accessible(clazz.getDeclaredConstructor(AbstractBlock.Settings.class))
                                               .newInstance(BlockSettingAccessor.ACCESSOR.get(clazz)),
                                       ex -> {
                                           ex.printStackTrace();
                                           return null;
                                       }
        );
    }

    public void writeNbt(BlockEntity entity, NbtCompound nbt) {
        this.nbtSerializeFramework.writeNbt(entity,
                                            nbt
        );
    }

    public void readNbt(BlockEntity entity, NbtCompound nbt) {
        this.nbtSerializeFramework.readNbt(entity,
                                           nbt
        );
    }

    public void initEntity(BlockEntity entity) {
        this.nbtSerializeFramework.init(entity);
    }

    public void properties(Block block, StateManager.Builder<Block, BlockState> builder) {
        Arrays.stream(block.getClass()
                           .getDeclaredFields())
              .peek(f -> ReflectionFramework.accessible(f,
                                                        block
              ))
              .filter(f -> f.isAnnotationPresent(AutoProperty.class))
              .forEach(field -> {
                  EntrustEnvironment.trys(() -> {
                      Property<?> properties = EntrustEnvironment.cast(field.get(block));
                      LOGGER.info("Building property '{}' as '{}' for block '{}' ",
                                  field.getName(),
                                  field.getType()
                                       .getName(),
                                  block.getClass()
                                       .getName()
                      );
                      if (properties == null) {
                          LOGGER.warn("Property '{}' as field is unable to access with '{}'",
                                      field.getName(),
                                      field.getType()
                                           .getName()
                          );
                      } else {
                          builder.add(properties);
                      }
                  });
              });
    }

    private boolean verify(Class<Block> block) {
        final List<String> missing = ApricotCollectionFactor.newArrayList();

        // Check indispensable fields, cannot register if missing.
        if (! IdentifierAccessor.ACCESSOR.has(block)) {
            missing.add("IDENTIFIER");
        }
        if (! BlockSettingAccessor.ACCESSOR.has(block)) {
            missing.add("SETTINGS or SETTING");
        }
        return checkFields(block
                                   .getName(),
                           missing
        );
    }

    private void build(Block block) {
        LOGGER.info("Building block: '{}'",
                    block.getClass()
                         .getName()
        );

        EntrustEnvironment.trys(() -> {
                                    Identifier identifier = IdentifierAccessor.ACCESSOR.get(block);

                                    // Register this block to vanilla.
                                    Registry.register(Registries.BLOCK,
                                                      identifier,
                                                      block
                                    );

                                    // Register this block to trtr.
                                    TrtrBlocks.register(identifier,
                                                        block
                                    );

                                    // Register block item.
                                    item(block);

                                    // Register block entity type
                                    entityType(block,
                                               identifier.toString()
                                    );

                                    this.blocks.add(block);

                                    // Call done method for custom action when done building.
                                    // Method automatic call need @Auto annotation always.
                                    Method method = block.getClass()
                                                         .getMethod("done");

            accessible(method);
            method.invoke(block);
                                }
        );
    }

    public BlockDataGenFramework dataGen() {
        return DATA_GEN;
    }

    public List<Block> dumpBlocks() {
        return ApricotCollectionFactor.newArrayList(this.blocks);
    }

    private void item(Block block) {
        EntrustEnvironment.trys(() -> {
                                    // Use block identifier for register block item.
                                    Identifier identifier = IdentifierAccessor.ACCESSOR.get(block);
                                    BlockItem item = EntrustEnvironment.trys(
                                            // If has created block item, use it directly, do not create again.
                                            () -> ExceptionEnvironment.nullWhen(() -> Objects.requireNonNull(BlockItemAccessor.ACCESSOR.get(block)),
                                                                                InvertOfControlException.class
                                            ),
                                            () -> {
                                                // If it has no done block item creating in block, create it by automatic.
                                                // Should use settings in block item.
                                                Class<? extends BlockItem> clazz = BlockItemAccessor.ACCESSOR.getType(block);
                                                try {
                                                    return item(clazz,
                                                                block
                                                    );
                                                } catch (NotStaticFieldException e) {
                                                    return null;
                                                }
                                            }
                                    );

                                    // Debug...
                                    if (item == null) {
                                        LOGGER.debug("Block '{}' has no item",
                                                     identifier
                                        );
                                        return;
                                    } else {
                                        LOGGER.debug("Block '{}' has item",
                                                     identifier
                                        );
                                    }

                                    LOGGER.info("Building block item '{}' for block '{}'",
                                                item.getClass()
                                                    .getName(),
                                                block.getClass()
                                                     .getName()
                                    );

                                    // Register this block item to vanilla.
                                    item.appendBlocks(Item.BLOCK_ITEMS,
                                                      item
                                    );

                                    Registry.register(Registries.ITEM,
                                                      identifier,
                                                      item
                                    );

                                    // Register this block item to trtr.
                                    TrtrBlockItems.register(identifier,
                                                            item
                                    );

                                    TrtrItems.register(identifier,
                                                       item
                                    );
                                }
        );
    }

    public BlockEntity createBlockEntity(Block block, BlockPos pos, BlockState state) {
        try {
            Class<? extends BlockEntity> clazz = BlockEntityAccessor.ACCESSOR.getType(block);
            return entity(clazz,
                          block,
                          pos,
                          state
            );
        } catch (NotStaticFieldException e) {
            return null;
        }
    }

    public void entityType(Class<? extends BlockEntity> clazz, Block block, String id) {
        TrtrBlockEntityFactory factory = (p, s) -> entity(clazz,
                                                          block,
                                                          p,
                                                          s
        );
        Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY,
                                          id
        );
        BlockEntityType<?> entityType = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                                                          id,
                                                          BlockEntityType.Builder.create(factory :: create,
                                                                                         block
                                                                         )
                                                                                 .build(type)
        );
        this.blockEntities.put(block.getClass(),
                               entityType
        );
    }

    public <T extends BlockEntity> BlockEntityType<T> entityType(Class<? extends Block> block) {
        return EntrustEnvironment.cast(this.blockEntities.get(block));
    }

    public void entityTick(World world, BlockPos pos, BlockState state, BlockEntity entity) {
        EntrustEnvironment.trys(
                () -> accessible(entity.getClass()
                                       .getMethod("tick",
                                                  World.class,
                                                  BlockPos.class,
                                                  BlockState.class,
                                                  entity.getClass()
                                       ))
                        .invoke(null,
                                world,
                                pos,
                                state,
                                entity
                        ),
                () -> accessible(entity.getClass()
                                       .getMethod("tick",
                                                  World.class,
                                                  BlockPos.class,
                                                  BlockState.class,
                                                  BlockEntity.class
                                       ))
                        .invoke(null,
                                world,
                                pos,
                                state,
                                entity
                        )
        );
    }

    public void render(Block block) {
        if (BlockEntityRenderAccessor.ACCESSOR.has(block)) {
            BlockEntityType<MudStoveBlockEntity> type = TrtrMod.BLOCK_FRAMEWORK.entityType(block.getClass());

            if (type == null) {
                LOGGER.warn("Block '{}' has RENDER or ENTITY_RENDER field was found, but missing block entity type, unable to build block entity render",
                            block.getClass()
                                 .getName()
                );
            }

            Class<? extends BlockEntityRenderer<?>> render = BlockEntityRenderAccessor.ACCESSOR.getType(block);

            LOGGER.info("Building block entity render '{}' for block '{}'",
                        render.getName(),
                        block.getClass()
                             .getName()
            );

            Receptacle<Boolean> hasCtx = Receptacle.of(true);

            Constructor<BlockEntityRenderer<?>> constructor = EntrustEnvironment.cast(EntrustEnvironment.trys(() -> render
                                                                                                                      .getConstructor(BlockEntityRendererFactory.Context.class),
                                                                                                              () -> {
                                                                                                                  hasCtx.set(false);
                                                                                                                  return block.getClass()
                                                                                                                              .getConstructor();
                                                                                                              }
            ));

            if (constructor == null) {
                LOGGER.warn("Block entity render '{}' of block '{}' is unable to construct, missing constructors: arg of (BlockEntityRendererFactory.Context.class) or no arg constructor",
                            render.getName(),
                            block.getClass()
                                 .getName()
                );
                return;
            }

            accessible(constructor);

            BlockEntityRendererFactories.register(type,
                                                  ctx -> EntrustEnvironment.cast(EntrustEnvironment.trys(() -> {
                                                      if (hasCtx.get()) {
                                                          return constructor.newInstance(ctx);
                                                      } else {
                                                          return constructor.newInstance();
                                                      }
                                                  }))
            );
        }
    }

    public void renders() {
        for (Block block : this.blocks) {
            render(block);
        }
    }

    public void entityType(Block block, String id) {
        Class<? extends BlockEntity> clazz = BlockEntityAccessor.ACCESSOR.getType(block);
        // Debug...
        if (clazz == null) {
            LOGGER.debug("Block '{}' has no item",
                         block.getClass()
                              .getName()
            );
            return;
        } else {
            LOGGER.debug("Block '{}' has item",
                         block.getClass()
                              .getName()
            );
        }

        LOGGER.info("Building block entity '{}' for block '{}'",
                    clazz.getName(),
                    block.getClass()
                         .getName()
        );

        entityType(clazz,
                   block,
                   id
        );
    }

    public BlockEntity entity(Class<? extends BlockEntity> clazz, Block block, BlockPos pos, BlockState state) {
        try {
            // Construct the block entity.
            return EntrustEnvironment.trys(() -> clazz.getConstructor(BlockEntityType.class,
                                                                      BlockPos.class,
                                                                      BlockState.class
                                                      )
                                                      .newInstance(this.blockEntities.get(block.getClass()),
                                                                   pos,
                                                                   state
                                                      ));
        } catch (Exception e) {
            return null;
        }
    }

    private BlockItem item(Class<? extends BlockItem> clazz, Block block) {
        try {
            // Construct the block item.
            return clazz.getConstructor(Block.class,
                                        Item.Settings.class
                        )
                        .newInstance(block,
                                     ItemSettingAccessor.ACCESSOR.get(clazz)
                        );
        } catch (Exception e) {
            return null;
        }
    }
}
