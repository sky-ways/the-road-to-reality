package com.github.cao.awa.trtr.framework.block;

import com.github.cao.awa.apricot.anntations.Auto;
import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.block.TrtrBlock;
import com.github.cao.awa.trtr.block.TrtrBlocks;
import com.github.cao.awa.trtr.framework.block.data.gen.BlockDataGenFramework;
import com.github.cao.awa.trtr.framework.block.item.BlockItemAccessor;
import com.github.cao.awa.trtr.framework.block.setting.BlockSettingAccessor;
import com.github.cao.awa.trtr.framework.identifier.IdentifierAccessor;
import com.github.cao.awa.trtr.framework.item.ItemSettingAccessor;
import com.github.cao.awa.trtr.framework.reflection.ReflectionFramework;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

public class BlockFramework extends ReflectionFramework {
    private static final Logger LOGGER = LogManager.getLogger("Trtr/BlockFramework");
    private final List<TrtrBlock> blocks = ApricotCollectionFactor.newArrayList();
    private final BlockDataGenFramework DATA_GEN = new BlockDataGenFramework(this);

    public void load() {
        getReflection().getTypesAnnotatedWith(Auto.class)
                       .stream()
                       .filter(TrtrBlock.class :: isAssignableFrom)
                       .map(this :: cast)
                       .filter(this :: verify)
                       .map(this :: instance)
                       .forEach(this :: build);
    }

    private Class<TrtrBlock> cast(Class<?> clazz) {
        return EntrustEnvironment.cast(clazz);
    }

    private TrtrBlock instance(Class<TrtrBlock> clazz) {
        // Construct the block using settings.
        // Here have not a default template settings for coping with settings missing.
        LOGGER.info("Constructing block: '{}'",
                    clazz.getName()
        );
        return EntrustEnvironment.trys(() -> clazz.getConstructor(AbstractBlock.Settings.class)
                                                  .newInstance(BlockSettingAccessor.ACCESSOR.get(clazz))
        );
    }

    private boolean verify(Class<TrtrBlock> block) {
        final List<String> missing = ApricotCollectionFactor.newArrayList();

        // Check indispensable fields, cannot register if missing.
        if (! IdentifierAccessor.ACCESSOR.has(block)) {
            missing.add("IDENTIFIER");
        }
        if (! BlockSettingAccessor.ACCESSOR.has(block)) {
            missing.add("SETTINGS or SETTING");
        }
        return verifyFields(block
                                    .getName(),
                            missing
        );
    }

    private void build(TrtrBlock block) {
        LOGGER.info("Building block: '{}'",
                    block.getClass()
                         .getName()
        );

        EntrustEnvironment.trys(() -> {
                                    Identifier identifier = IdentifierAccessor.ACCESSOR.get(block);

                                    // Register this block.
                                    Registry.register(Registries.BLOCK,
                                                      identifier,
                                                      block
                                    );

                                    TrtrBlocks.register(identifier,
                                                        block
                                    );

                                    // Register block item.
                                    item(block);

                                    this.blocks.add(block);

                                    // Call done method for custom action when done building.
                                    Method method = block.getClass()
                                                         .getMethod("done");
                                    method.invoke(block);
                                }
        );
    }

    public BlockDataGenFramework dataGen() {
        return DATA_GEN;
    }

    public List<TrtrBlock> dumpBlocks() {
        return ApricotCollectionFactor.newArrayList(this.blocks);
    }

    private void item(TrtrBlock block) {
        EntrustEnvironment.trys(() -> {
                                    // Use block identifier for register block item.
                                    Identifier identifier = IdentifierAccessor.ACCESSOR.get(block);
                                    BlockItem item = EntrustEnvironment.trys(
                                            // If has created block item, use it directly, do not create again.
                                            () -> Objects.requireNonNull(BlockItemAccessor.ACCESSOR.get(block)),
                                            () -> {
                                                // If it has no done block item creating in block, create it by automatic.
                                                // Should use settings in block item.
                                                Class<? extends BlockItem> clazz = BlockItemAccessor.ACCESSOR.getType(block);
                                                return clazz.getConstructor(Block.class,
                                                                            Item.Settings.class
                                                            )
                                                            .newInstance(block,
                                                                         ItemSettingAccessor.ACCESSOR.get(clazz)
                                                            );
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

                                    // Register block item to vanilla registry.
                                    item.appendBlocks(Item.BLOCK_ITEMS,
                                                      item
                                    );

                                    Registry.register(Registries.ITEM,
                                                      identifier,
                                                      item
                                    );
                                }
        );
    }
}
