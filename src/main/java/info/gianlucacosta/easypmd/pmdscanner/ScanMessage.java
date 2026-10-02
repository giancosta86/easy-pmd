package info.gianlucacosta.easypmd.pmdscanner;

import info.gianlucacosta.easypmd.ide.options.Options;
import java.io.Serializable;
import org.netbeans.spi.tasklist.Task;
import org.openide.filesystems.FileObject;
import org.openide.text.Annotation;

/**
 * One of the messages emitted as a result of a PMD scan
 */
public interface ScanMessage extends Serializable {

    boolean isShowableInGuardedSections();

    int getLineNumber();

    Task createTask(Options options, FileObject fileObject);

    Annotation createAnnotation(Options options);
}
