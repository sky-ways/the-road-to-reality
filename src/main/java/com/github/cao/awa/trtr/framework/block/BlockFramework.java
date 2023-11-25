package com.github.cao.awa.trtr.framework.block;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.TrtrMod;
import com.github.cao.awa.trtr.annotation.property.AutoProperty;
import com.github.cao.awa.trtr.block.TrtrBlocks;
import com.github.cao.awa.trtr.block.item.TrtrBlockItems;
import com.github.cao.awa.trtr.constant.trtr.TrtrConstants;
import com.github.cao.awa.trtr.framework.accessor.block.entity.BlockEntityAccessor;
import com.github.cao.awa.trtr.framework.accessor.block.entity.TrtrBlockEntityFactory;
import com.github.cao.awa.trtr.framework.accessor.block.item.BlockItemAccessor;
import com.github.cao.awa.trtr.framework.accessor.block.setting.BlockSettingAccessor;
import com.github.cao.awa.trtr.framework.accessor.identifier.IdentifierAccessor;
import com.github.cao.awa.trtr.framework.accessor.item.ItemSettingAccessor;
import com.github.cao.awa.trtr.framework.block.data.gen.BlockDataGenFramework;
import com.github.cao.awa.trtr.framework.exception.InvertOfControlException;
import com.github.cao.awa.trtr.framework.exception.NotStaticFieldException;
import com.github.cao.awa.trtr.framework.reflection.ReflectionFramework;
import com.github.cao.awa.trtr.item.TrtrItems;
import com.github.cao.awa.trtr.renderer.block.BlockRendererProvider;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.ExceptionEnvironment;
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

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * <p>
 * The proxy framework of 'Block' and anything 'Block' related.
 * </p>
 * <p>
 * Targeted to 'Block', 'BlockEntity', 'BlockItem' and other.
 * </p>
 *
 * @author cao_awa
 * @author 草二号机
 * @since 1.0.0
 */
public class BlockFramework extends ReflectionFramework {
    private static final Logger LOGGER = LogManager.getLogger("BlockFramework");
    private final List<Block> blocks = ApricotCollectionFactor.arrayList();
    private final Map<Class<? extends Block>, BlockEntityType<?>> blockEntities = ApricotCollectionFactor.hashMap();
    private final List<Identifier> alreadyRegistered = ApricotCollectionFactor.arrayList();
    private final BlockDataGenFramework dataGen = new BlockDataGenFramework(this);

    public void work() {
        // Working stream...
        reflection().getTypesAnnotatedWith(Auto.class)
                    .stream()
                    .filter(this :: match)
                    .map(this :: cast)
                    .filter(this :: verify)
                    .map(this :: instance)
                    .forEach(this :: build);
    }

    private boolean match(Class<?> clazz) {
        // Framework will not process the unsupported class.
        boolean unsupported = unsupported(clazz);
        boolean dev = dev(clazz);

        // The abstract class cannot be instanced, filter it out.
        boolean abs = Modifier.isAbstract(clazz.getModifiers());

        // And framework can process only 'Block', if not then reject the process in this framework.
        boolean matchType = Block.class.isAssignableFrom(clazz);

        if (! matchType) {
            return false;
        }

        // Notice the unsupported class.
        if (unsupported) {
            LOGGER.warn("Class '{}' is unsupported, ignored it",
                        clazz.getName()
            );
        }

        // Notice development class.
        if (dev && ! TrtrMod.DEV_MODE) {
            LOGGER.warn("Class '{}' is only available in development environment, ignored it",
                        clazz.getName()
            );
        }

        // Combine conditions.
        return
                // Ignored dev check when dev mode enabled.
                (TrtrMod.DEV_MODE || ! dev) &&
                        // Unsupported class will not be proxy.
                        ! unsupported &&
                        // Abstract class will not be proxy.
                        ! abs &&
                        shouldLoad(TrtrConstants.getLoadingSide(clazz));
    }

    /**
     * <p>
     * Cast the class type to class of 'Block'.
     * </p>
     *
     * @param clazz target class
     * @return class of block
     * @author cao_awa
     * @since 1.0.0
     */
    private Class<Block> cast(Class<?> clazz) {
        // This cast should not move to map calls in working stream, EntrustEnvironment did not know what type you want!
        return EntrustEnvironment.cast(clazz);
    }

    /**
     * <p>
     * Mapping class of 'Block' to 'Block' instance.
     * </p>
     *
     * @param clazz target class
     * @return block instance
     * @author cao_awa
     * @since 1.0.0
     */
    private Block instance(Class<Block> clazz) {
        // Construct the block using settings.
        // Here have not a default template settings for coping with settings missing.
        LOGGER.info("Constructing block: '{}'",
                    clazz.getName()
        );
        return EntrustEnvironment.trys(
                // Ensure accessible and create instance.
                () -> accessible(clazz.getDeclaredConstructor(AbstractBlock.Settings.class))
                        .newInstance(BlockSettingAccessor.ACCESSOR.get(clazz)),
                ex -> {
                    // Notice the errors.
                    LOGGER.warn("Failed create block instance",
                                ex
                    );
                    return null;
                }
        );
    }

    /**
     * <p>
     * Proxy method to calls 'writeNbt' for the block entity.
     * </p>
     *
     * @param entity block entity
     * @param nbt    nbt
     * @author cao_awa
     * @since 1.0.0
     */
    public void writeNbt(BlockEntity entity, NbtCompound nbt) {
        TrtrMod.NBT_SERIALIZE_FRAMEWORK.writeNbt(entity,
                                                 nbt
        );
    }

    /**
     * <p>
     * Proxy method to calls 'readNbt' for the block entity.
     * </p>
     *
     * @param entity block entity
     * @param nbt    nbt
     * @author cao_awa
     * @since 1.0.0
     */
    public void readNbt(BlockEntity entity, NbtCompound nbt) {
        TrtrMod.NBT_SERIALIZE_FRAMEWORK.readNbt(entity,
                                                nbt
        );
    }

    /**
     * <p>
     * Proxy method call by 'TrtrBlock' constructor.
     * </p>
     *
     * @param entity block entity
     * @author cao_awa
     * @since 1.0.0
     */
    public void initEntity(BlockEntity entity) {
        TrtrMod.NBT_SERIALIZE_FRAMEWORK.init(entity);
    }

    /**
     * <p>
     * Proxy method to append block properties.
     * </p>
     *
     * @param block   block instance
     * @param builder block state builder
     * @author cao_awa
     * @author 草二号机
     * @since 1.0.0
     */
    public void properties(Block block, StateManager.Builder<Block, BlockState> builder) {
        Arrays.stream(block.getClass()
                           .getDeclaredFields())
              // Ensure annotation presents.
              .filter(f -> f.isAnnotationPresent(AutoProperty.class))
              // Ensure field accessible.
              .map(f -> ReflectionFramework.accessible(f,
                                                       block
              ))
              // Do appends.
              .forEach(field -> EntrustEnvironment.trys(() -> {
                  // Get and ensure it is a property.
                  Object fieldResult = field.get(block);

                  if (! (fieldResult instanceof Property<?>)) {
                      LOGGER.debug("Property in '{}' is not block state property, got type '{}', ignored in block framework",
                                   block.getClass()
                                        .getName(),
                                   fieldResult.getClass()
                                              .getName()
                      );
                      return;
                  }
                  Property<?> properties = EntrustEnvironment.cast(fieldResult);

                  LOGGER.info("Building property '{}' as '{}' for block '{}' ",
                              field.getName(),
                              field.getType()
                                   .getName(),
                              block.getClass()
                                   .getName()
                  );
                  // If properties is null, it will not be appended successfully.
                  // Notice this problem to logs.
                  if (properties == null) {
                      LOGGER.warn("Property '{}' as field is unable to access with '{}'",
                                  field.getName(),
                                  field.getType()
                                       .getName()
                      );
                  } else {
                      // Append to builder.
                      builder.add(properties);
                  }
              }));
    }

    /**
     * <p>
     * Verify the proxy requirement field in the class presents.
     * </p>
     *
     * @param block class of block
     * @return field correct
     * @author cao_awa
     * @since 1.0.0
     */
    private boolean verify(Class<Block> block) {
        final List<String> missing = ApricotCollectionFactor.arrayList();

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

                                    // Do not build null identifier block.
                                    // Null identifier means something was wrong.
                                    if (identifier == null) {
                                        LOGGER.error("Got null identifier, cancel building block '{}'",
                                                     block.getClass()
                                                          .getName()
                                        );
                                        return;
                                    }

                                    // Do not register the duplicate identifier.
                                    if (this.alreadyRegistered.contains(identifier)) {
                                        LOGGER.error("The identifier '{}' already registered, duplicate identifier will not be register successful",
                                                     identifier
                                        );
                                        return;
                                    }

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

                                    // Add to lists.
                                    this.blocks.add(block);
                                    this.alreadyRegistered.add(identifier);

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
        return this.dataGen;
    }

    public List<Block> dumpBlocks() {
        return ApricotCollectionFactor.arrayList(this.blocks);
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

    public <T extends BlockEntity> BlockEntityType<T> entityType(Class<? extends Block> block) {
        return EntrustEnvironment.cast(this.blockEntities.get(block));
    }

    public void entityTick(World world, BlockPos pos, BlockState state, BlockEntity entity) {
        EntrustEnvironment.trys(
                // Invoke method that static World, BlockPos, BlockState, <T extends BlockEntity> T.
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
                () -> EntrustEnvironment.trys(
                        // Does not match to <T>, change to BlockEntity.
                        // Invoke method that static World, BlockPos, BlockState, BlockEntity.
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
                                ),
                        () -> {
                            // Not static tick method.
                            // Invoke no block entity in parameter method.
                            accessible(entity.getClass()
                                             .getMethod("tick",
                                                        World.class,
                                                        BlockPos.class,
                                                        BlockState.class
                                             ),
                                       entity
                            ).invoke(entity,
                                     world,
                                     pos,
                                     state
                            );
                        }
                )
        );
    }

    public void renderer(Block block) {
        if (block instanceof BlockRendererProvider<?> provider) {
            BlockEntityType<BlockEntity> type = TrtrMod.BLOCK_FRAMEWORK.entityType(block.getClass());

            if (type == null) {
                LOGGER.warn("Block '{}' has RENDER or ENTITY_RENDER field was found, but missing block entity type, unable to build block entity render",
                            block.getClass()
                                 .getName()
                );
                return;
            }

            Function<BlockEntityRendererFactory.Context, BlockEntityRenderer<?>> render = EntrustEnvironment.cast(provider.renderer());

            LOGGER.info("Building block entity render for block '{}'",
                        block.getClass()
                             .getName()
            );


            BlockEntityRendererFactories.register(
                    type,
                    ctx -> EntrustEnvironment.cast(render.apply(ctx))
            );
        }
    }

    public void renderers() {
        for (Block block : this.blocks) {
            renderer(block);
        }
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

    public void entityType(Block block, String id) {
        Class<? extends BlockEntity> clazz = BlockEntityAccessor.ACCESSOR.getType(block);
        // Debug...
        if (clazz == null) {
            LOGGER.debug("Block '{}' has no entity",
                         block.getClass()
                              .getName()
            );
            return;
        } else {
            LOGGER.debug("Block '{}' has entity",
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
}
