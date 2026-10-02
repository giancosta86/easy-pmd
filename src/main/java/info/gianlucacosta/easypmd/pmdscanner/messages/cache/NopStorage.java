package info.gianlucacosta.easypmd.pmdscanner.messages.cache;

import java.util.Optional;

/**
 * Storage doing just nothing
 */
public class NopStorage implements CacheStorage {

    @Override
    public Optional<CacheEntry> getEntry(String pathString) {
        return Optional.empty();
    }

    @Override
    public void putEntry(String pathString, CacheEntry cacheEntry) {
    }

    @Override
    public boolean clearEntries() {
        return true;
    }

    @Override
    public void close() {
    }
}
