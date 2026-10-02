package info.gianlucacosta.easypmd.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Throwable-related utilities
 */
public interface Throwables {

    /**
     * Converts the stack trace of a Throwable to a string
     *
     * @param throwable
     * @return the string representation of the stack trace
     */
    static String getStackTraceString(Throwable throwable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);

        throwable.printStackTrace(printWriter);

        return result.toString();
    }

    /**
     * Returns the throwable message, or the message of its cause, recursively,
     * or a simple message if no message was found in the chain
     *
     * @param throwable The subject throwable
     * @return a non-empty string
     */
    static String getNonEmptyMessage(Throwable throwable) {
        String message = throwable.getMessage();

        if (message == null || message.isEmpty()) {
            Throwable cause = throwable.getCause();

            if (cause != null) {
                return getNonEmptyMessage(cause);
            } else {
                return "(no message)";
            }
        } else {
            return message;
        }
    }

    /**
     * Returns a string showing a Throwable (type and message) and the related
     * stack trace
     *
     * @param throwable
     * @return
     */
    static String toStringWithStackTrace(Throwable throwable) {
        return String.format(
                "%s (%s)\n%s",
                throwable.getClass().getName(),
                throwable.getMessage(),
                getStackTraceString(throwable)
        );
    }
}
