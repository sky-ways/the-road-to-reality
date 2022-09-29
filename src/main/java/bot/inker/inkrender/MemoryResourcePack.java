package bot.inker.inkrender;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectRBTreeMap;
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
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MemoryResourcePack implements ResourcePack {
    public static final String NAMESPACE = "inker_render_memory";
    public static final MemoryResourcePack INSTANCE = new MemoryResourcePack();
    private static final Logger LOGGER = LogManager.getLogger();
    private final AtomicLong idAlloc = new AtomicLong();
    private final Long2ObjectMap<Supplier<InputStream>> registers = new Long2ObjectRBTreeMap<>();

    public Identifier put(Supplier<InputStream> supplier) {
        long id = idAlloc.getAndIncrement();
        registers.put(id, supplier);
        Identifier identifier = Identifier.of(NAMESPACE, String.valueOf(id));
        LOGGER.debug("put() = {}", identifier);
        return identifier;
    }

    @Nullable
    @Override
    public InputStream openRoot(String fileName) {
        return null;
    }

    @Override
    public InputStream open(ResourceType type, Identifier id) throws IOException {
        LOGGER.debug("open({},{})", type, id);
        Supplier<InputStream> supplier = registers.get(getIdFromIdentifier(id));
        if (supplier == null) {
            throw new IOException("MemoryResource '" + id.getPath() + "' not found");
        }
        InputStream resource;
        try {
            resource = supplier.get();
        } catch (Exception e) {
            throw new IOException("MemoryResource '" + id.getPath() + "' supplier throw " + e.getClass().getName() + ": '" + e.getMessage() + "'");
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
                if ('0' <= theChar && theChar <= '9') {
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
        LOGGER.debug("contains({},{})", type, id);
        return registers.containsKey(getIdFromIdentifier(id));
    }

    @Override
    public Set<String> getNamespaces(ResourceType type) {
        return Set.of(NAMESPACE);
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
