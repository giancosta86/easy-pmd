package info.gianlucacosta.easypmd.pmdscanner.messages.cache;

import info.gianlucacosta.easypmd.pmdscanner.ScanMessage;

import java.util.Optional;
import java.util.Set;

/**
 * Cache of scan messages
 */
public interface ScanMessagesCache extends AutoCloseable {

    Optional<Set<ScanMessage>> getScanMessagesFor(String pathString, long pathLastModificationMillis);

    void putScanMessagesFor(String pathString, long lastModificationMillis, Set<ScanMessage> scanMessages);

    boolean clear();
}
