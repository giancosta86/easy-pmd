package info.gianlucacosta.easypmd.ide.annotations;

import info.gianlucacosta.easypmd.ide.options.Options;
import info.gianlucacosta.easypmd.pmdscanner.ScanMessage;
import org.openide.cookies.LineCookie;
import org.openide.filesystems.FileChangeAdapter;
import org.openide.filesystems.FileChangeListener;
import org.openide.filesystems.FileEvent;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.text.Annotation;
import org.openide.text.Line;
import org.openide.util.lookup.ServiceProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Default implementation of AnnotationService
 */
@ServiceProvider(service = AnnotationService.class)
public class DefaultAnnotationService implements AnnotationService {

    private final Map<FileObject, Set<Annotation>> attachedAnnotations = new HashMap<>();
    private final Map<FileObject, FileChangeListener> registeredFileChangeListeners = new HashMap<>();

    @Override
    public void attachAnnotationsTo(Options options, DataObject dataObject, Set<ScanMessage> scanMessages) {
        final FileObject fileObject = dataObject.getPrimaryFile();

        if (attachedAnnotations.containsKey(fileObject)) {
            throw new IllegalArgumentException("EasyPmd annotations have already been attached to this file.\nYou must detach them before attaching new ones.");
        }

        LineCookie lineCookie = dataObject.getLookup().lookup(LineCookie.class);
        Line.Set lineSet = lineCookie.getLineSet();

        Set<Annotation> annotations = scanMessages
                .stream()
                .map(scanMessage -> {
                    Annotation annotation = scanMessage.createAnnotation(options);

                    Line line = lineSet.getOriginal(scanMessage.getLineNumber() - 1);
                    annotation.attach(line);
                    return annotation;
                }).collect(Collectors.toSet());

        attachedAnnotations.put(fileObject, annotations);

        registerFileEventHandlers(fileObject);
    }

    private void registerFileEventHandlers(final FileObject fileObject) {
        FileChangeListener fileChangeListener = new FileChangeAdapter() {
            @Override
            public void fileChanged(FileEvent ev) { //This is triggered only when the user SAVES the file
                detachAnnotationsFrom(fileObject);
            }

            @Override
            public void fileDeleted(FileEvent ev) {
                detachAnnotationsFrom(fileObject);
            }
        };

        fileObject.addFileChangeListener(fileChangeListener);
        registeredFileChangeListeners.put(fileObject, fileChangeListener);
    }

    @Override
    public void detachAnnotationsFrom(FileObject fileObject) {
        Set<Annotation> fileAnnotations = attachedAnnotations.get(fileObject);

        if (fileAnnotations == null) {
            return;
        }

        fileAnnotations.forEach(annotation -> {
            annotation.detach();
        });

        attachedAnnotations.remove(fileObject);

        unregisterFileEventHandlers(fileObject);
    }

    private void unregisterFileEventHandlers(FileObject fileObject) {
        FileChangeListener fileChangeListener = registeredFileChangeListeners.get(fileObject);

        fileObject.removeFileChangeListener(fileChangeListener);

        registeredFileChangeListeners.remove(fileObject);
    }

    @Override
    public void detachAllAnnotations() {
        Collection<FileObject> fileObjects = new ArrayList<>(attachedAnnotations.keySet());

        fileObjects.forEach(fileObject -> {
            detachAnnotationsFrom(fileObject);
        });
    }
}
