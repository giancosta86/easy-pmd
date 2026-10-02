package info.gianlucacosta.easypmd.pmdscanner.messages.cache;

import java.util.Optional;

/**
 * Persistent cache backend. It should provide its own locking and
 * synchronization
 */
public interface CacheStorage {

    Optional<CacheEntry> getEntry(String pathString);

    void putEntry(String pathString, CacheEntry cacheEntry);

    boolean clearEntries();

    void close() throws Exception;
}
