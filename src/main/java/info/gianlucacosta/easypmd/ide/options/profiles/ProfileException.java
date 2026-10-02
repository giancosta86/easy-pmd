package info.gianlucacosta.easypmd.ide.options.profiles;

/**
 * Exception related to the management of Profile objects.
 */
public class ProfileException extends Exception {

    public ProfileException(String message) {
        super(message);
    }

    public ProfileException(String message, Throwable cause) {
        super(message, cause);
    }
}
