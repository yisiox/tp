package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CommandHistoryTest {

    private final CommandHistory commandHistory = new CommandHistory();

    @Test
    void getPrevious_emptyHistory_returnsEmptyString() {
        assertEquals("", commandHistory.getPrevious());
    }

    @Test
    void getNext_emptyHistory_returnsEmptyString() {
        assertEquals("", commandHistory.getNext());
    }

    @Test
    void getPreviousAndGetNext_multipleStrings_returnsCorrectStrings() {
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
        assertEquals("", commandHistory.getPrevious());

        // loop back to the front of the history
        assertEquals(text1, commandHistory.getNext());
        assertEquals(text2, commandHistory.getNext());
        assertEquals(text3, commandHistory.getNext());
        assertEquals("", commandHistory.getNext());
    }

    @Test
    void addAtMiddleOfHistory_multipleStrings_returnsCorrectStrings() {
        // tests the case where a string is added while the pointer is at the middle of the history.

        String text1 = "text1";
        String text2 = "text2";
        String text3 = "text3";
        String text4 = "text4";
        String text5 = "text5";

        commandHistory.add(text1);
        commandHistory.add(text2);
        commandHistory.add(text3);
        commandHistory.add(text4);

        // move pointer to middle
        assertEquals(text4, commandHistory.getPrevious());
        assertEquals(text3, commandHistory.getPrevious());

        commandHistory.add(text5);

        // verify all strings are in order
        assertEquals(text5, commandHistory.getPrevious());
        assertEquals(text4, commandHistory.getPrevious());
        assertEquals(text3, commandHistory.getPrevious());
        assertEquals(text2, commandHistory.getPrevious());
        assertEquals(text1, commandHistory.getPrevious());
        assertEquals("", commandHistory.getPrevious());
    }

    @Test
    void rightCornerAdd_anyText_success() {
        // tests the corner case on the right side of the history

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
    void leftCornerAdd_anyText_success() {
        // tests the corner case on the left side of the history

        String text = "list";
        commandHistory.add(text);

        // getPrevious() should return the correct string
        assertEquals(text, commandHistory.getPrevious());

        // getPrevious() should return an empty string
        assertEquals("", commandHistory.getNext());

        // getNext() should return the correct string again
        assertEquals(text, commandHistory.getPrevious());

        // getPrevious() should return an empty string again
        assertEquals("", commandHistory.getNext());
    }

}
