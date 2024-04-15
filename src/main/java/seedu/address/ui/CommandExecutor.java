package seedu.address.ui;

import seedu.address.commons.exceptions.CommandHistoryException;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.util.exceptions.ParseException;

/**
 * Represents an API which supports command execution and history tracking.
 */
public interface CommandExecutor {

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    String execute(String commandText) throws CommandException, ParseException, DataLoadingException;

    /**
     * Gets the previous command's text.
     *
     * @return The previous command's text.
     * @throws CommandHistoryException If the message history is empty or the index is already at the start.
     */
    String getPreviousCommandText() throws CommandHistoryException;

    /**
     * Gets the next command's text.
     *
     * @return The next command's text.
     * @throws CommandHistoryException If the index is already at the end.
     */
    String getNextCommandText() throws CommandHistoryException;

}
