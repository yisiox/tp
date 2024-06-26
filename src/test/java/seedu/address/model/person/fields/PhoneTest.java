package seedu.address.model.person.fields;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    private static final String WHITESPACE = " \t\r\n";
    private static final String INVALID_PHONE = "+6512345678 (home)";
    private static final String VALID_PHONE_1 = "+65 1234 5678, +98-4321-5432-42";
    private static final String VALID_PHONE_2 = "999";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Phone("")); // empty string
        assertThrows(IllegalArgumentException.class, () -> new Phone(" ")); // spaces only
        assertThrows(IllegalArgumentException.class, () -> new Phone("91")); // less than 3 numbers
        assertThrows(IllegalArgumentException.class, () -> new Phone("phone")); // non-numeric
        assertThrows(IllegalArgumentException.class, () -> new Phone("9011p041")); // alphabets within digits
        assertThrows(IllegalArgumentException.class, () -> new Phone("9312_1534")); // underscore within digits
        assertThrows(IllegalArgumentException.class, () -> new Phone("93121534(home), 94387573(office)")); // brackets
    }

    @Test
    public void constructor_validPhone_throwsIllegalArgumentException() {
        assertDoesNotThrow(() -> new Phone("911")); // exactly 3 numbers
        assertDoesNotThrow(() -> new Phone("93121534"));
        assertDoesNotThrow(() -> new Phone("124293842033123")); // long phone numbers
    }

    @Test
    public void of_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Phone.of((String) null));
    }

    @Test
    public void of_invalidValue_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Phone.of(INVALID_PHONE));
    }

    @Test
    public void of_validValueWithoutWhitespace_returnsPhone() throws Exception {
        assertEquals(new Phone(VALID_PHONE_1), Phone.of(VALID_PHONE_1));
    }

    @Test
    public void of_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE_1 + WHITESPACE;
        assertEquals(new Phone(VALID_PHONE_1), Phone.of(phoneWithWhitespace));
    }

    @Test
    public void equals() {
        Phone phone = new Phone(VALID_PHONE_1);

        // same values -> returns true
        Phone phone2 = new Phone(VALID_PHONE_1);
        assertTrue(phone.equals(phone2));
        assertEquals(phone.hashCode(), phone2.hashCode());

        // same object -> returns true
        assertTrue(phone.equals(phone));
        assertEquals(phone.hashCode(), phone.hashCode());

        // null -> returns false
        assertFalse(phone.equals(null));

        // different types -> returns false
        assertFalse(phone.equals(5.0f));

        // different values -> returns false
        Phone otherPhone = new Phone(VALID_PHONE_2);
        assertFalse(phone.equals(otherPhone));
    }

}
