package seedu.address.model.person.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

class TagsTest {

    private static final String INVALID_TAG = "#friend";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String[] emptyStringArray = new String[0];

    private static final Tag[] emptyTagArray = new Tag[0];

    @Test
    public void constructor_emptyArray_success() {
        assertEquals(new Tags(emptyTagArray), new Tags(emptyStringArray));
    }

    @Test
    public void of_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Tags.of(null));
    }

    @Test
    public void of_collectionWithInvalidTags_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Tags.of(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void of_emptyCollection_returnsEmptyTags() {
        Tags emptyTags = new Tags(new Tag[0]);
        Tags emptyTags2 = Tags.of(Collections.emptyList());
        assertEquals(emptyTags, emptyTags2);
        assertEquals(emptyTags.hashCode(), emptyTags2.hashCode());
    }

    @Test
    public void of_collectionWithValidTags_returnsTagSet() {
        Tags actualTags = Tags.of(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Tags expectedTags = new Tags(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2));

        assertEquals(expectedTags, actualTags);
        assertEquals(expectedTags.hashCode(), actualTags.hashCode());
    }

    @Test
    public void equals() {
        Tags tags = new Tags(VALID_TAG_1);

        // same values -> returns true
        Tags tags2 = new Tags(VALID_TAG_1);
        assertTrue(tags.equals(tags2));
        assertEquals(tags.hashCode(), tags2.hashCode());

        // same object -> returns true
        assertTrue(tags.equals(tags));
        assertEquals(tags.hashCode(), tags.hashCode());

        // null -> returns false
        assertFalse(tags.equals(null));

        // different types -> returns false
        assertFalse(tags.equals(5.0f));

        // different values -> returns false
        Tags otherTags = new Tags(VALID_TAG_2);
        assertFalse(tags.equals(otherTags));
    }

}
