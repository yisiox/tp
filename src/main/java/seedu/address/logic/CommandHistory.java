package seedu.address.logic;

import java.util.ArrayList;

import seedu.address.commons.exceptions.CommandHistoryException;

//@@author aureliony
/**
 * Represents an abstraction for a command history data structure.
 */
public class CommandHistory {

    public static final String MESSAGE_HISTORY_EMPTY = "Command history is empty.";
    public static final String MESSAGE_INDEX_START = "Command history index is already at the start.";
    public static final String MESSAGE_INDEX_END = "Command history index is already at the end.";

    private final ArrayList<String> commandHistory = new ArrayList<>();
    private int index = 0;

    /**
     * Get the previous command text.
     *
     * @return The previous command text.
     * @throws CommandHistoryException If the message history is empty or the index is already at the start.
     */
    public String getPrevious() throws CommandHistoryException {
        assert 0 <= index && index <= commandHistory.size();

        if (commandHistory.isEmpty()) {
            throw new CommandHistoryException(MESSAGE_HISTORY_EMPTY);
        }

        if (index == 0) {
            throw new CommandHistoryException(MESSAGE_INDEX_START);
        }

        index -= 1;
        return commandHistory.get(index);
    }

    /**
     * Get the next command text.
     *
     * @return The next command text.
     * @throws CommandHistoryException If the index is already at the end.
     */
    public String getNext() throws CommandHistoryException {
        assert 0 <= index && index <= commandHistory.size();

        if (index == commandHistory.size()) {
            throw new CommandHistoryException(MESSAGE_INDEX_END);
        }

        index += 1;

        if (index == commandHistory.size()) {
            return "";
        }

        return commandHistory.get(index);
    }

    /**
     * Add a valid command text to the command history.
     *
     * @param commandText The command text string.
     */
    public void add(String commandText) {
        commandHistory.add(commandText);
        index = commandHistory.size();
    }

}
