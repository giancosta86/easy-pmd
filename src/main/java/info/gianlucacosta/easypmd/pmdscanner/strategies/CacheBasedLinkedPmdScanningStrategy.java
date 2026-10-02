package info.gianlucacosta.easypmd.pmdscanner.strategies;

import info.gianlucacosta.easypmd.ide.PathService;
import info.gianlucacosta.easypmd.ide.Injector;
import info.gianlucacosta.easypmd.ide.options.Options;
import info.gianlucacosta.easypmd.pmdscanner.ScanMessage;
import info.gianlucacosta.easypmd.pmdscanner.messages.ScanError;
import info.gianlucacosta.easypmd.pmdscanner.messages.cache.ScanMessagesCache;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Scanning strategy looking for cached scan messages: if they are missing, a
 * default PMD scan is performed.
 */
public class CacheBasedLinkedPmdScanningStrategy extends LinkedPmdScanningStrategy {

    private static final Logger logger = Logger.getLogger(CacheBasedLinkedPmdScanningStrategy.class.getName());

    private final ScanMessagesCache scanMessagesCache = Injector.lookup(ScanMessagesCache.class);

    public CacheBasedLinkedPmdScanningStrategy(Options options) {
        super(options);
    }

    @Override
    public Set<ScanMessage> scan(Path path) {
        try {
            String pathString = path.toString();
            long lastModificationMillis = Files.getLastModifiedTime(path).toMillis();

            final Optional<Set<ScanMessage>> cachedScanMessagesOption = scanMessagesCache.getScanMessagesFor(
                    pathString,
                    lastModificationMillis
            );

            return cachedScanMessagesOption.orElseGet(() -> {
                logger.info(() -> String.format("No valid cache entry found for path: %s", pathString));

                Set<ScanMessage> scanMessages = super.scan(path);

                scanMessagesCache.putScanMessagesFor(pathString, lastModificationMillis, scanMessages);

                return scanMessages;
            });
        } catch (IOException ex) {
            return Collections.singleton(
                    new ScanError(ex)
            );
        }
    }
}
