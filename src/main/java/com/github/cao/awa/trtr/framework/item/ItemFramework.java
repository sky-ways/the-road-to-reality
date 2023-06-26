package com.github.cao.awa.trtr.framework.item;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.TrtrMod;
import com.github.cao.awa.trtr.annotation.BlockBelong;
import com.github.cao.awa.trtr.framework.accessor.identifier.IdentifierAccessor;
import com.github.cao.awa.trtr.framework.accessor.item.ItemSettingAccessor;
import com.github.cao.awa.trtr.framework.item.data.gen.ItemDataGenFramework;
import com.github.cao.awa.trtr.framework.reflection.ReflectionFramework;
import com.github.cao.awa.trtr.item.TrtrItems;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

public class ItemFramework extends ReflectionFramework {
    private static final Logger LOGGER = LogManager.getLogger("ItemFramework");
    private final ItemDataGenFramework dataGen = new ItemDataGenFramework(this);
    private final List<Item> items = ApricotCollectionFactor.newArrayList();
    private final List<Identifier> alreadyRegistered = ApricotCollectionFactor.newArrayList();

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
        boolean matchType = Item.class.isAssignableFrom(clazz);

        if (! matchType) {
            return false;
        }

        boolean blockBelong = clazz.isAnnotationPresent(BlockBelong.class);

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

        // The item belong to a block, will be processed by block framework.
        // Do not process items that belong to the block.
        if (blockBelong) {
            return false;
        }

        // Combine conditions.
        return
                // Ignored dev check when dev mode enabled.
                (TrtrMod.DEV_MODE || ! dev) &&
                        // Unsupported class will not be proxy.
                        ! unsupported &&
                        // Abstract class will not be proxy.
                        ! abs;
    }

    /**
     * <p>
     * Cast the class type to class of 'Item'.
     * </p>
     *
     * @param clazz target class
     * @return class of block
     * @author cao_awa
     * @since 1.0.0
     */
    private Class<Item> cast(Class<?> clazz) {
        // This cast should not move to map calls in working stream, EntrustEnvironment did not know what type you want!
        return EntrustEnvironment.cast(clazz);
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
    private boolean verify(Class<Item> block) {
        final List<String> missing = ApricotCollectionFactor.newArrayList();

        // Check indispensable fields, cannot register if missing.
        if (! IdentifierAccessor.ACCESSOR.has(block)) {
            missing.add("IDENTIFIER");
        }
        return checkFields(block
                                   .getName(),
                           missing
        );
    }

    /**
     * <p>
     * Mapping class of 'Item' to 'Item' instance.
     * </p>
     *
     * @param clazz target class
     * @return block instance
     * @author cao_awa
     * @since 1.0.0
     */
    private Item instance(Class<Item> clazz) {
        // Construct the item using settings.
        // Here have not a default template settings for coping with settings missing.
        LOGGER.info("Constructing item: '{}'",
                    clazz.getName()
        );
        return EntrustEnvironment.trys(
                // Ensure accessible and create instance.
                () -> accessible(clazz.getDeclaredConstructor(Item.Settings.class))
                        .newInstance(ItemSettingAccessor.ACCESSOR.get(clazz)),
                () -> EntrustEnvironment.trys(
                        () -> accessible(clazz.getDeclaredConstructor())
                                .newInstance(),
                        ex -> {
                            // Notice the errors.
                            LOGGER.warn("Failed create item instance",
                                        ex
                            );
                            return null;
                        }
                )
        );
    }

    private void build(Item item) {
        LOGGER.info("Building item: '{}'",
                    item.getClass()
                        .getName()
        );

        EntrustEnvironment.trys(() -> {
                                    Identifier identifier = IdentifierAccessor.ACCESSOR.get(item);

            // Do not build null identifier item.
            // Null identifier means something was wrong.
            if (identifier == null) {
                LOGGER.error("Got null identifier, cancel building item '{}'",
                             item.getClass()
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
            Registry.register(Registries.ITEM,
                              identifier,
                              item
            );

            // Register this block to trtr.
            TrtrItems.register(identifier,
                               item
                                    );

            // Add to lists.
            this.items.add(item);
            this.alreadyRegistered.add(identifier);

                                    // Call done method for custom action when done building.
                                    // Method automatic call need @Auto annotation always.
                                    Method method = item.getClass()
                                                        .getMethod("done");

                                    accessible(method);
                                    method.invoke(item);
                                }
        );
    }

    public ItemDataGenFramework dataGen() {
        return this.dataGen;
    }

    public List<Item> dumpItems() {
        return ApricotCollectionFactor.newArrayList(this.items);
    }
}
