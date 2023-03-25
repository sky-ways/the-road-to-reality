package bot.inker.inkrender;

import com.github.cao.awa.trtr.util.string.StringConcat;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.option.BiOption;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.resource.InputSupplier;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.metadata.ResourceMetadataReader;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

public class MemoryResourcePack implements ResourcePack {
    public static final String NAMESPACE = "inker_render_memory";
    public static final Set<String> NAMESPACES = Set.of(NAMESPACE);
    public static final MemoryResourcePack INSTANCE = MemoryResourcePack.arbitrary();
    private static final Logger LOGGER = LogManager.getLogger();
    private final AtomicLong idAlloc = new AtomicLong();
    private final Map<Long, Supplier<InputStream>> registers;
    private final BiOption<Boolean> async;

    private MemoryResourcePack(BiOption<Boolean> async) {
        this.registers = async.first() ? new ConcurrentHashMap<>() : new Long2ObjectOpenHashMap<>();
        this.async = async;
    }

    private static MemoryResourcePack async() {
        return new MemoryResourcePack(BiOption.of(
                true,
                false
        ));
    }

    private static MemoryResourcePack sync() {
        return new MemoryResourcePack(BiOption.of(
                false,
                true
        ));
    }

    private static MemoryResourcePack arbitrary() {
        return new MemoryResourcePack(BiOption.of(
                false,
                false
        ));
    }

    public Identifier put(Supplier<InputStream> supplier) {
        if (this.async.second()) {
            synchronized (this) {
                return put0(supplier);
            }
        }
        return put0(supplier);
    }

    private Identifier put0(Supplier<InputStream> supplier) {
        long id = this.idAlloc.getAndIncrement();
        this.registers.put(
                id,
                supplier
        );
        Identifier identifier = Identifier.of(
                NAMESPACE,
                String.valueOf(id)
        );
        LOGGER.debug(
                "put() = {}",
                identifier
        );
        return identifier;
    }

    public int allocated() {
        return this.idAlloc.intValue();
    }

    public BiOption<Boolean> asyncOption() {
        return this.async;
    }

    @Nullable
    @Override
    public InputSupplier<InputStream> openRoot(String... segments) {
        return null;
    }

    @Override
    public InputSupplier<InputStream> open(ResourceType type, Identifier identifier) {
        if (this.async.second()) {
            synchronized (this) {
                return open0(
                        type,
                        identifier
                );
            }
        }
        return open0(
                type,
                identifier
        );
    }

    public InputSupplier<InputStream> open0(ResourceType type, Identifier id) {
        LOGGER.debug(
                "open({},{})",
                type,
                id
        );
        Supplier<InputStream> supplier = this.registers.get(getIdFromIdentifier(id));
        if (supplier == null) {
            throw new RuntimeException(StringConcat.concat("MemoryResource '",
                                                           id.getPath(),
                                                           "' not found"
            ));
        }
        return InputSupplier.create(Path.of(id.getPath()));
    }

    private long getIdFromIdentifier(Identifier identifier) {
        try {
            String input = identifier.getPath();
            if (input.endsWith(".mcmeta")) {
                return - 1;
            }
            StringBuilder buf = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                char theChar = input.charAt(i);
                if (! ('0' > theChar || theChar > '9')) {
                    buf.append(theChar);
                }
            }
            return Long.parseLong(buf.toString());
        } catch (Exception e) {
            return - 1;
        }
    }

    @Override
    public void findResources(ResourceType type, String namespace, String prefix, ResultConsumer consumer) {

    }

    @Override
    public Set<String> getNamespaces(ResourceType type) {
        return NAMESPACES;
    }

    @Nullable
    @Override
    public <T> T parseMetadata(ResourceMetadataReader<T> metaReader) {
        return null;
    }

    @Override
    public String getName() {
        return NAMESPACE;
    }

    @Override
    public void close() {

    }
}
