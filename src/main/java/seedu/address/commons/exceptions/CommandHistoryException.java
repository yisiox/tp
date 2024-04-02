package seedu.address.commons.exceptions;

/**
 * Represents an error that occurred while navigating the command history.
 */
public class CommandHistoryException extends Exception {

    public CommandHistoryException(String message) {
        super(message);
    }

}
