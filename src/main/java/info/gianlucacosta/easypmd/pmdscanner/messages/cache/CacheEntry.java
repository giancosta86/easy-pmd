package info.gianlucacosta.easypmd.pmdscanner.messages.cache;

import info.gianlucacosta.easypmd.pmdscanner.ScanMessage;
import java.util.Set;

/**
 * Entry for the in-memory cache
 */
public class CacheEntry {

    private final long lastModificationMillis;
    private final Set<ScanMessage> scanMessages;

    public CacheEntry(long lastModificationMillis, Set<ScanMessage> scanMessages) {
        this.lastModificationMillis = lastModificationMillis;
        this.scanMessages = scanMessages;
    }

    public long getLastModificationMillis() {
        return lastModificationMillis;
    }

    public Set<ScanMessage> getScanMessages() {
        return scanMessages;
    }
}
