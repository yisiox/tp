package seedu.address.ui;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.util.exceptions.ParseException;
import seedu.address.storage.exceptions.StorageException;

/**
 * Represents an API which supports command execution and history tracking.
 */
public interface CommandExecutor {

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    String execute(String commandText) throws CommandException, ParseException, StorageException;

    /**
     * Gets the previous command's text.
     *
     * @return the previous command's text.
     */
    String getPreviousCommandText();

    /**
     * Gets the next command's text.
     *
     * @return the next command's text.
     */
    String getNextCommandText();

}
