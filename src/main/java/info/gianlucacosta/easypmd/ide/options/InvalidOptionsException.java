package info.gianlucacosta.easypmd.ide.options;

/**
 * Exception thrown when the options are invalid
 */
public class InvalidOptionsException extends Exception {

    public InvalidOptionsException() {
    }

    public InvalidOptionsException(String message) {
        super(message);
    }

    public InvalidOptionsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidOptionsException(Throwable cause) {
        super(cause);
    }

    public InvalidOptionsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
