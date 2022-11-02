package bot.inker.inkrender;

import com.github.zhuaidadaya.rikaishinikui.handler.option.*;
import it.unimi.dsi.fastutil.longs.*;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.metadata.ResourceMetadataReader;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
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
    public InputStream openRoot(String fileName) {
        return null;
    }

    @Override
    public InputStream open(ResourceType type, Identifier identifier) throws IOException {
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

    public InputStream open0(ResourceType type, Identifier id) throws IOException {
        LOGGER.debug(
                "open({},{})",
                type,
                id
        );
        Supplier<InputStream> supplier = this.registers.get(getIdFromIdentifier(id));
        if (supplier == null) {
            throw new IOException("MemoryResource '" + id.getPath() + "' not found");
        }
        InputStream resource;
        try {
            resource = supplier.get();
        } catch (Exception e) {
            throw new IOException("MemoryResource '" + id.getPath() + "' supplier throw " + e.getClass()
                                                                                             .getName() + ": '" + e.getMessage() + "'");
        }
        if (resource == null) {
            throw new IOException("MemoryResource '" + id.getPath() + "' can't open");
        }
        return resource;
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
    public Collection<Identifier> findResources(ResourceType type, String namespace, String prefix, Predicate<Identifier> allowedPathPredicate) {
        return Collections.emptyList();
    }

    @Override
    public boolean contains(ResourceType type, Identifier id) {
        if (this.async.second()) {
            synchronized (this) {
                return this.contains0(
                        type,
                        id
                );
            }
        }
        return this.contains0(
                type,
                id
        );
    }

    private boolean contains0(ResourceType type, Identifier id) {
        LOGGER.debug(
                "contains({},{})",
                type,
                id
        );
        return this.registers.containsKey(getIdFromIdentifier(id));
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
