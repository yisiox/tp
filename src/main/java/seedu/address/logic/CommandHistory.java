package seedu.address.logic;

import java.util.ArrayList;

/**
 * Represents an abstraction for a command history data structure.
 */
public class CommandHistory {

    private final ArrayList<String> commandHistory = new ArrayList<>();
    private int index = 0;

    /**
     * Get the previous command text.
     *
     * @return the previous command text.
     */
    public String getPrevious() {
        if (index <= 0) {
            return "";
        }

        index -= 1;
        return commandHistory.get(index);
    }

    /**
     * Get the next command text.
     *
     * @return the next command text.
     */
    public String getNext() {
        if (index >= commandHistory.size() - 1) {
            return "";
        }

        index += 1;
        return commandHistory.get(index);
    }

    /**
     * Add a valid command text to the command history.
     *
     * @param commandText the command text string.
     */
    public void add(String commandText) {
        commandHistory.add(commandText);
        index = commandHistory.size();
    }

}
