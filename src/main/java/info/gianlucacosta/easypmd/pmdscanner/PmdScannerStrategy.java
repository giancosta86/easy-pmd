package info.gianlucacosta.easypmd.pmdscanner;

import java.nio.file.Path;
import java.util.Set;

/**
 * Strategy used by the PMD scanner to scan files
 */
public interface PmdScannerStrategy {

    Set<ScanMessage> scan(Path path);
}
