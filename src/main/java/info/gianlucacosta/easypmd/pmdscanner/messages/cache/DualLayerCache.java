package info.gianlucacosta.easypmd.pmdscanner.messages.cache;

import info.gianlucacosta.easypmd.pmdscanner.ScanMessage;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Dual-layered (in-memory and in-storage) cache
 */
public class DualLayerCache implements ScanMessagesCache {

    private static final Logger logger = Logger.getLogger(DualLayerCache.class.getName());

    private final Map<String, CacheEntry> inMemoryEntries = new ConcurrentHashMap<>();

    private final CacheStorage storage;

    public DualLayerCache(CacheStorage storage) {
        this.storage = storage;
    }

    @Override
    public Optional<Set<ScanMessage>> getScanMessagesFor(String pathString, long pathLastModificationMillis) {
        CacheEntry inMemoryEntry = inMemoryEntries.get(pathString);

        if (inMemoryEntry != null) {
            if (inMemoryEntry.getLastModificationMillis() == pathLastModificationMillis) {
                logger.info(() -> String.format("Up-to-date in-memory hit for path: %s", pathString));
                return Optional.of(inMemoryEntry.getScanMessages());
            } else {
                logger.info(() -> String.format("Obsolete in-memory hit for path: %s", pathString));
                return Optional.empty();
            }
        } else {
            Optional<CacheEntry> storageEntryOption = storage.getEntry(pathString);

            return storageEntryOption.map(storageEntry -> {
                if (storageEntry.getLastModificationMillis() == pathLastModificationMillis) {
                    logger.info(() -> String.format("Up-to-date on-storage hit for path: %s", pathString));
                    inMemoryEntries.put(pathString, storageEntry);

                    return storageEntry.getScanMessages();
                } else {
                    logger.info(() -> String.format("Obsolete on-storage hit for path: %s", pathString));
                    return Collections.emptySet();
                }
            });
        }
    }

    @Override
    public void putScanMessagesFor(String pathString, long lastModificationMillis, Set<ScanMessage> scanMessages) {
        CacheEntry cacheEntry = new CacheEntry(lastModificationMillis, scanMessages);

        inMemoryEntries.put(pathString, cacheEntry);
        storage.putEntry(pathString, cacheEntry);
    }

    @Override
    public boolean clear() {
        inMemoryEntries.clear();

        return storage.clearEntries();
    }

    @Override
    public void close() throws Exception {
        inMemoryEntries.clear();

        storage.close();
    }
}
