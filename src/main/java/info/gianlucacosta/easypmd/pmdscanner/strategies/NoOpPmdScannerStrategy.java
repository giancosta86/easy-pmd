package info.gianlucacosta.easypmd.pmdscanner.strategies;

import info.gianlucacosta.easypmd.pmdscanner.PmdScannerStrategy;
import info.gianlucacosta.easypmd.pmdscanner.ScanMessage;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Set;

/**
 * Scanning strategy that always returns an empty set
 */
public class NoOpPmdScannerStrategy implements PmdScannerStrategy {

    @Override
    public Set<ScanMessage> scan(Path path) {
        return Collections.emptySet();
    }
}
