package info.gianlucacosta.easypmd.ide.annotations;

import org.openide.text.Annotation;

/**
 * Basic annotation subclass that can be instantiated
 */
public class BasicAnnotation extends Annotation {

    private final String annotationType;
    private final String message;

    public BasicAnnotation(String annotationType, String message) {
        this.annotationType = annotationType;
        this.message = message;
    }

    @Override
    public String getAnnotationType() {
        return annotationType;
    }

    @Override
    public String getShortDescription() {
        return message;
    }
}
