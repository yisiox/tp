package seedu.address.model.person.fields;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    private static final String WHITESPACE = " \t\r\n";
    private static final String INVALID_NAME = " ";
    private static final String VALID_NAME_1 = "Rachel Walker";
    private static final String VALID_NAME_2 = "Sarah Baker";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Name("")); // empty string
        assertThrows(IllegalArgumentException.class, () -> new Name(" ")); // spaces only
        assertThrows(IllegalArgumentException.class, () -> new Name("\\")); // contains '\'
        assertThrows(IllegalArgumentException.class, () -> new Name("peter\\")); // contains '\'
    }

    @Test
    public void constructor_validName_success() {
        assertDoesNotThrow(() -> new Name("peter jack")); // alphabets only
        assertDoesNotThrow(() -> new Name("12345")); // numbers only
        assertDoesNotThrow(() -> new Name("peter the 2nd")); // alphanumeric characters
        assertDoesNotThrow(() -> new Name("Capital Tan")); // with capital letters
        assertDoesNotThrow(() -> new Name("David Roger Jackson Ray Jr 2nd")); // long names
        assertDoesNotThrow(() -> new Name("Tan Ah Kow, Aaron")); // contains special characters
        assertDoesNotThrow(() -> new Name("Muthu s/o Magesh")); // contains special characters
    }

    @Test
    public void of_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Name.of((String) null));
    }

    @Test
    public void of_invalidValue_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Name.of(INVALID_NAME));
    }

    @Test
    public void of_validValueWithoutWhitespace_returnsName() throws Exception {
        assertEquals(new Name(VALID_NAME_1), Name.of(VALID_NAME_1));
    }

    @Test
    public void of_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME_1 + WHITESPACE;
        assertEquals(new Name(VALID_NAME_1), Name.of(nameWithWhitespace));
    }

    @Test
    public void equals() {
        Name name = new Name(VALID_NAME_1);

        // same values -> returns true
        Name name2 = new Name(VALID_NAME_1);
        assertTrue(name.equals(name2));
        assertEquals(name.hashCode(), name2.hashCode());

        // same object -> returns true
        assertTrue(name.equals(name));
        assertEquals(name.hashCode(), name.hashCode());

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        Name otherName = new Name(VALID_NAME_2);
        assertFalse(name.equals(otherName));
    }

}
