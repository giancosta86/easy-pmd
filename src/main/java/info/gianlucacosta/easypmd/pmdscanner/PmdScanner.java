package info.gianlucacosta.easypmd.pmdscanner;

import info.gianlucacosta.easypmd.pmdscanner.strategies.NoOpPmdScannerStrategy;
import info.gianlucacosta.easypmd.pmdscanner.strategies.LinkedPmdScanningStrategy;
import info.gianlucacosta.easypmd.pmdscanner.strategies.CacheBasedLinkedPmdScanningStrategy;
import info.gianlucacosta.easypmd.pmdscanner.messages.ScanError;
import info.gianlucacosta.easypmd.ide.options.Options;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Scans files using PMD, returning a list of ScanMessage for each scanned file
 */
public class PmdScanner {

    private static final Logger logger = Logger.getLogger(PmdScanner.class.getName());

    private final PmdScannerStrategy strategy;

    public PmdScanner(Options options) {
        if (options.getRuleSets().isEmpty()) {
            logger.info(() -> "Setting a NOP scanning strategy");
            strategy = new NoOpPmdScannerStrategy();
            return;
        }

        if (options.isUseScanMessagesCache()) {
            logger.info(() -> "Setting a cached scanning strategy");
            strategy = new CacheBasedLinkedPmdScanningStrategy(options);
        } else {
            logger.info(() -> "Setting a non-cached scanning strategy");
            strategy = new LinkedPmdScanningStrategy(options);
        }
    }

    public Set<ScanMessage> scan(Path path) {
        try {
            return strategy.scan(path);
        } catch (Exception ex) {
            ScanError scanError = new ScanError(ex);

            return Collections.singleton(
                    scanError
            );
        }
    }
}
