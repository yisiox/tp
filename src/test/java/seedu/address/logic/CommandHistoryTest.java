package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.CommandHistoryException;

class CommandHistoryTest {

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

        String text1 = "text1";
        String text2 = "text2";
        String text3 = "text3";

        commandHistory.add(text1);
        commandHistory.add(text2);
        commandHistory.add(text3);

        // cycle through the entire history
        assertEquals(text3, commandHistory.getPrevious());
        assertEquals(text2, commandHistory.getPrevious());
        assertEquals(text1, commandHistory.getPrevious());
        assertThrows(CommandHistoryException.class, commandHistory::getPrevious, CommandHistory.MESSAGE_INDEX_START);

        // loop back to the front of the history
        assertEquals(text2, commandHistory.getNext());
        assertEquals(text3, commandHistory.getNext());
        assertEquals("", commandHistory.getNext());
        assertThrows(CommandHistoryException.class, commandHistory::getNext, CommandHistory.MESSAGE_INDEX_END);
    }

    @Test
    public void addAtMiddleOfHistory_multipleStrings_success() throws CommandHistoryException {
        // tests the case where a string is added while the index is at the middle of the history.

        String text1 = "text1";
        String text2 = "text2";
        String text3 = "text3";
        String text4 = "text4";
        String text5 = "text5";

        commandHistory.add(text1);
        commandHistory.add(text2);
        commandHistory.add(text3);
        commandHistory.add(text4);

        // move index to middle
        assertEquals(text4, commandHistory.getPrevious());
        assertEquals(text3, commandHistory.getPrevious());

        commandHistory.add(text5);

        // verify all strings are in order
        assertEquals(text5, commandHistory.getPrevious());
        assertEquals(text4, commandHistory.getPrevious());
        assertEquals(text3, commandHistory.getPrevious());
        assertEquals(text2, commandHistory.getPrevious());
        assertEquals(text1, commandHistory.getPrevious());
        assertThrows(CommandHistoryException.class, commandHistory::getPrevious, CommandHistory.MESSAGE_INDEX_START);
    }

    @Test
    public void rightBoundaryGet_anyText_success() throws CommandHistoryException {
        // tests the right boundary of the command history

        String text = "list";
        commandHistory.add(text);

        // getPrevious() should return the correct string
        assertEquals(text, commandHistory.getPrevious());

        // getNext() should return an empty string
        assertEquals("", commandHistory.getNext());

        // getPrevious() should return the correct string again
        assertEquals(text, commandHistory.getPrevious());

        // getNext() should return an empty string again
        assertEquals("", commandHistory.getNext());
    }

    @Test
    public void leftBoundaryGet_anyText_success() throws CommandHistoryException {
        // tests the left boundary of the command history

        String text = "list";
        commandHistory.add(text);

        // getPrevious() should return the correct string
        assertEquals(text, commandHistory.getPrevious());

        // getPrevious() should throw an exception
        assertThrows(CommandHistoryException.class, commandHistory::getPrevious, CommandHistory.MESSAGE_INDEX_START);

        // getNext() should return an empty string
        assertEquals("", commandHistory.getNext());

        // getPrevious() should return the correct string
        assertEquals(text, commandHistory.getPrevious());

        // getPrevious() should throw an exception again
        assertThrows(CommandHistoryException.class, commandHistory::getPrevious, CommandHistory.MESSAGE_INDEX_START);
    }

}
