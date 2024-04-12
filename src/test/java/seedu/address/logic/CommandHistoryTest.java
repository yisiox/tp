package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.CommandHistoryException;

class CommandHistoryTest {

    private static final String emptyString = "";
    private static final String commandText1 = "text1";
    private static final String commandText2 = "text2";
    private static final String commandText3 = "text3";
    private static final String commandText4 = "text4";
    private static final String commandText5 = "text5";

    private final CommandHistory commandHistory = new CommandHistory();

    @Test
    public void getPrevious_emptyHistory_throwsCommandException() {
        assertThrows(CommandHistoryException.class, commandHistory::getPrevious, CommandHistory.MESSAGE_HISTORY_EMPTY);
    }

    @Test
    public void getNext_emptyHistory_throwsCommandException() {
        assertThrows(CommandHistoryException.class, commandHistory::getNext, CommandHistory.MESSAGE_HISTORY_EMPTY);
    }

    @Test
    public void getPreviousAndGetNext_multipleStrings_success() throws CommandHistoryException {
        // tests the simple case of cycling through the history
        commandHistory.add(commandText1);
        commandHistory.add(commandText2);
        commandHistory.add(commandText3);

        // cycle through the entire history
        assertEquals(commandText3, commandHistory.getPrevious());
        assertEquals(commandText2, commandHistory.getPrevious());
        assertEquals(commandText1, commandHistory.getPrevious());
        assertThrows(CommandHistoryException.class, commandHistory::getPrevious, CommandHistory.MESSAGE_INDEX_START);

        // loop back to the front of the history
        assertEquals(commandText2, commandHistory.getNext());
        assertEquals(commandText3, commandHistory.getNext());
        assertEquals(emptyString, commandHistory.getNext());
        assertThrows(CommandHistoryException.class, commandHistory::getNext, CommandHistory.MESSAGE_INDEX_END);
    }

    @Test
    public void addAtMiddleOfHistory_multipleStrings_success() throws CommandHistoryException {
        // tests the case where a string is added while the index is at the middle of the history
        commandHistory.add(commandText1);
        commandHistory.add(commandText2);
        commandHistory.add(commandText3);
        commandHistory.add(commandText4);

        // move index to middle
        assertEquals(commandText4, commandHistory.getPrevious());
        assertEquals(commandText3, commandHistory.getPrevious());

        commandHistory.add(commandText5);

        // verify all strings are in order
        assertEquals(commandText5, commandHistory.getPrevious());
        assertEquals(commandText4, commandHistory.getPrevious());
        assertEquals(commandText3, commandHistory.getPrevious());
        assertEquals(commandText2, commandHistory.getPrevious());
        assertEquals(commandText1, commandHistory.getPrevious());
        assertThrows(CommandHistoryException.class, commandHistory::getPrevious, CommandHistory.MESSAGE_INDEX_START);
    }

    @Test
    public void rightBoundaryGet_anyText_success() throws CommandHistoryException {
        // tests the right boundary of the command history
        commandHistory.add(commandText1);

        // getPrevious() should return the correct string
        assertEquals(commandText1, commandHistory.getPrevious());

        // getNext() should return an empty string
        assertEquals(emptyString, commandHistory.getNext());

        // getPrevious() should return the correct string again
        assertEquals(commandText1, commandHistory.getPrevious());

        // getNext() should return an empty string again
        assertEquals(emptyString, commandHistory.getNext());
    }

    @Test
    public void leftBoundaryGet_anyText_success() throws CommandHistoryException {
        // tests the left boundary of the command history
        commandHistory.add(commandText1);

        // getPrevious() should return the correct string
        assertEquals(commandText1, commandHistory.getPrevious());

        // getPrevious() should throw an exception
        assertThrows(CommandHistoryException.class, commandHistory::getPrevious, CommandHistory.MESSAGE_INDEX_START);

        // getNext() should return an empty string
        assertEquals(emptyString, commandHistory.getNext());

        // getPrevious() should return the correct string
        assertEquals(commandText1, commandHistory.getPrevious());

        // getPrevious() should throw an exception again
        assertThrows(CommandHistoryException.class, commandHistory::getPrevious, CommandHistory.MESSAGE_INDEX_START);
    }

}
