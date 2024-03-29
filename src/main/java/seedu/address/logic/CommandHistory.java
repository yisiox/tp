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
        assert -1 <= index && index <= commandHistory.size();

        if (index < 0) {
            return "";
        }

        index -= 1;

        if (index < 0) {
            return "";
        }

        return commandHistory.get(index);
    }

    /**
     * Get the next command text.
     *
     * @return the next command text.
     */
    public String getNext() {
        assert -1 <= index && index <= commandHistory.size();

        if (index >= commandHistory.size()) {
            return "";
        }

        index += 1;

        if (index >= commandHistory.size()) {
            return "";
        }

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
