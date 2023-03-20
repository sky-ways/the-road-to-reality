package com.github.cao.awa.trtr.framework.block;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.TrtrMod;
import com.github.cao.awa.trtr.annotation.property.AutoProperty;
import com.github.cao.awa.trtr.annotation.serializer.AutoNbt;
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
import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializable;
import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializer;
import com.github.cao.awa.trtr.framework.nbt.serializer.item.NbtItemStackSerializer;
import com.github.cao.awa.trtr.framework.nbt.serializer.type.raw.*;
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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BlockFramework extends ReflectionFramework {
    private static final Logger LOGGER = LogManager.getLogger("Trtr/BlockFramework");
    private final List<Block> blocks = ApricotCollectionFactor.newArrayList();
    private final Map<Class<? extends Block>, BlockEntityType<?>> blockEntities = ApricotCollectionFactor.newHashMap();
    private final BlockDataGenFramework DATA_GEN = new BlockDataGenFramework(this);
    private final Map<Class<?>, NbtSerializer<?>> nbtSerializers = ApricotCollectionFactor.newHashMap();

    public void work() {
        initNbtSerializers();

        // Working stream...
        getReflection().getTypesAnnotatedWith(Auto.class)
                       .stream()
                       .filter(this :: match)
                       .map(this :: cast)
                       .filter(this :: verify)
                       .map(this :: block)
                       .forEach(this :: build);
    }

    private void initNbtSerializers() {
        registerNbtSerializer(new NbtItemStackSerializer(),
                              ItemStack.class
        );
        registerNbtSerializer(new NbtBooleanSerializer(),
                              boolean.class,
                              Boolean.class
        );
        registerNbtSerializer(new NbtByteSerializer(),
                              byte.class,
                              Byte.class
        );
        registerNbtSerializer(new NbtCharSerializer(),
                              char.class,
                              Character.class
        );
        registerNbtSerializer(new NbtShortSerializer(),
                              short.class,
                              Short.class
        );
        registerNbtSerializer(new NbtIntSerializer(),
                              int.class,
                              Integer.class
        );
        registerNbtSerializer(new NbtLongSerializer(),
                              long.class,
                              Long.class
        );
        registerNbtSerializer(new NbtBigIntegerSerializer(),
                              BigInteger.class
        );
        registerNbtSerializer(new NbtFloatSerializer(),
                              float.class,
                              Float.class
        );
        registerNbtSerializer(new NbtDoubleSerializer(),
                              double.class,
                              Double.class
        );
        registerNbtSerializer(new NbtBigDecimalSerializer(),
                              BigDecimal.class
        );
        registerNbtSerializer(new NbtStringSerializer(),
                              String.class
        );
    }

    @SafeVarargs
    public final <T> void registerNbtSerializer(NbtSerializer<T> serializer, Class<T>... type) {
        for (Class<T> t : type) {
            this.nbtSerializers.put(t,
                                    serializer
            );
        }
    }

    public <T> NbtSerializer<T> getNbtSerializer(Class<T> type) {
        return EntrustEnvironment.cast(this.nbtSerializers.get(type));
    }

    private boolean match(Class<?> clazz) {
        // The abstract class cannot be instanced, filter it out.
        // And framework can process only Block, if not then reject the process in this framework.
        return ! Modifier.isAbstract(clazz.getModifiers()) && Block.class.isAssignableFrom(clazz) && checkDev(clazz);
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
        return EntrustEnvironment.trys(() -> ensureAccessible(clazz.getDeclaredConstructor(AbstractBlock.Settings.class))
                                               .newInstance(BlockSettingAccessor.ACCESSOR.get(clazz)),
                                       ex -> {
                                           ex.printStackTrace();
                                           return null;
                                       }
        );
    }

    public void writeNbt(BlockEntity entity, NbtCompound nbt) {
        Arrays.stream(entity.getClass()
                            .getDeclaredFields())
              .peek(f -> ReflectionFramework.ensureAccessible(f,
                                                              entity
              ))
              .filter(f -> f.isAnnotationPresent(AutoNbt.class))
              .forEach(field -> {
                  String name = field.getAnnotation(AutoNbt.class)
                                     .value();
                  name = name.equals("") ? field.getName() : name;

                  NbtElement element = null;

                  String failedCause = null;

                  try {
                      Object f = field.get(entity);

                      if (f instanceof NbtSerializable serializable) {
                          element = serializable.toNbt();
                      } else {
                          NbtSerializer<?> nbtSerializer = getNbtSerializer(field.getType());
                          if (nbtSerializer != null) {
                              element = nbtSerializer.serialize(EntrustEnvironment.cast(f));
                          } else {
                              failedCause = "'" + field.getName() + "' is not nbt serializable and missing serializer of type";
                          }
                      }
                  } catch (Exception ex) {
                      failedCause = "exception " + ex;
                  }

                  if (element == null) {
                      if (failedCause == null) {
                          LOGGER.warn("Failed to write field '{}' as type '{}' to nbt for block entity '{}' at '{}' by unknown reason",
                                      field.getName(),
                                      field.getType()
                                           .getName(),
                                      entity.getClass()
                                            .getName(),
                                      entity.getPos()
                          );
                      } else {
                          LOGGER.warn("Failed to write field '{}' as type '{}' to nbt for block entity '{}' at '{}' because {}",
                                      field.getName(),
                                      field.getType()
                                           .getName(),
                                      entity.getClass()
                                            .getName(),
                                      entity.getPos(),
                                      failedCause
                          );
                      }
                      return;
                  }

                  nbt.put(name,
                          element
                  );
                  LOGGER.debug("Block entity '{}' at {} has written nbt for field '{}': {}",
                               entity.getClass()
                                     .getName(),
                               entity.getPos(),
                               field.getName(),
                               element
                  );
              });
    }

    public void readNbt(BlockEntity entity, NbtCompound nbt) {
        Arrays.stream(entity.getClass()
                            .getDeclaredFields())
              .peek(f -> ReflectionFramework.ensureAccessible(f,
                                                              entity
              ))
              .filter(f -> f.isAnnotationPresent(AutoNbt.class))
              .forEach(field -> {
                  EntrustEnvironment.trys(() -> {
                      String name = field.getAnnotation(AutoNbt.class)
                                         .value();
                      name = name.equals("") ? field.getName() : name;

                      NbtElement element = nbt.get(name);

                      if (NbtSerializable.class.isAssignableFrom(field.getType()) && ensureAccessible(field.getType()
                                                                                                           .getConstructor()).newInstance() instanceof NbtSerializable serializer) {
                          serializer.fromNbt(element);
                          field.set(entity,
                                    serializer
                          );
                          LOGGER.debug("Block entity '{}' at {} reading nbt for field '{}': {}",
                                       entity.getClass()
                                             .getName(),
                                       entity.getPos(),
                                       field.getName(),
                                       element
                          );
                      } else {
                          NbtSerializer<?> nbtSerializer = getNbtSerializer(field.getType());
                          if (nbtSerializer != null) {
                              field.set(entity,
                                        nbtSerializer.deserialize(element)
                              );
                              LOGGER.debug("Block entity '{}' at {} reading nbt for field '{}': {}",
                                           entity.getClass()
                                                 .getName(),
                                           entity.getPos(),
                                           field.getName(),
                                           element
                              );
                          } else {
                              LOGGER.warn("The field in block entity '{}' at {} is not nbt serializable and missing serializer of type '{}'",
                                          entity.getClass()
                                                .getName(),
                                          entity.getPos(),
                                          field.getType()
                                               .getName()
                              );
                          }
                      }
                  });
              });
    }

    public void initEntity(BlockEntity entity) {
        Arrays.stream(entity.getClass()
                            .getDeclaredFields())
              .peek(f -> ReflectionFramework.ensureAccessible(f,
                                                              entity
              ))
              .filter(f -> f.isAnnotationPresent(AutoNbt.class))
              .forEach(field -> {
                  EntrustEnvironment.trys(() -> {
                      try {
                          NbtSerializer<?> serializer = getNbtSerializer(field.getType());
                          if (serializer != null) {
                              field.set(entity,
                                        serializer.initializer()
                              );
                          } else {
                              Object o = ensureAccessible(field.getType()
                                                               .getConstructor()).newInstance();
                              if (o instanceof NbtSerializable serializable) {
                                  field.set(entity,
                                            serializable
                                  );
                                  LOGGER.debug("Block entity '{}' at {} initialized @AutoNbt field '{}' as type '{}'",
                                               entity.getClass()
                                                     .getName(),
                                               entity.getPos(),
                                               field.getName(),
                                               serializable.getClass()
                                                           .getName()
                                  );
                              }
                          }
                      } catch (Exception e) {
                          LOGGER.warn("Block entity '{}' at {} unable to initialize @AutoNbt field '{}' with type '{}'",
                                      entity.getClass()
                                            .getName(),
                                      entity.getPos(),
                                      field.getName(),
                                      field.getType()
                                           .getName()
                          );
                      }
                  });
              });
    }

    public void properties(Block block, StateManager.Builder<Block, BlockState> builder) {
        Arrays.stream(block.getClass()
                           .getDeclaredFields())
              .peek(f -> ReflectionFramework.ensureAccessible(f,
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

                                    ensureAccessible(method);
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
                () -> ensureAccessible(entity.getClass()
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
                () -> ensureAccessible(entity.getClass()
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

            ensureAccessible(constructor);

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
