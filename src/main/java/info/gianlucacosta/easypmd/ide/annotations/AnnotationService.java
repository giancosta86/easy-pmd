package info.gianlucacosta.easypmd.ide.annotations;

import info.gianlucacosta.easypmd.ide.options.Options;
import info.gianlucacosta.easypmd.pmdscanner.ScanMessage;
import java.util.Set;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;

public interface AnnotationService {

    void attachAnnotationsTo(Options options, DataObject dataObject, Set<ScanMessage> scanMessages);

    void detachAnnotationsFrom(FileObject fileObject);

    void detachAllAnnotations();
}
