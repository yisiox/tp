package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonMatchesQueryPredicateTest {

    private Predicate<Person> predicate;

    @Test
    public void equals() {
        String firstPredicateQuery = "first";
        String secondPredicateQuery = "second";

        Predicate<Person> firstPredicate = new PersonMatchesQueryPredicate(firstPredicateQuery);
        Predicate<Person> secondPredicate = new PersonMatchesQueryPredicate(secondPredicateQuery);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        Predicate<Person> firstPredicateCopy = new PersonMatchesQueryPredicate(firstPredicateQuery);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameMatchesQuery_returnsTrue() {
        // Partial match
        predicate = new PersonMatchesQueryPredicate("Ali");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Exact string match
        predicate = new PersonMatchesQueryPredicate("Alice Bob");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Mixed-case string match
        predicate = new PersonMatchesQueryPredicate("aLIce bOB");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Short query, long name
        predicate = new PersonMatchesQueryPredicate("y");
        assertTrue(predicate.test(new PersonBuilder().withName("Alex Yeoh The Fifth").build()));

        // Query without whitespace
        predicate = new PersonMatchesQueryPredicate("xy");
        assertTrue(predicate.test(new PersonBuilder().withName("Alex Yeoh").build()));
    }

    @Test
    public void test_invalidString_throwsException() {
        // Empty string should throw an exception
        assertThrows(AssertionError.class, () -> new PersonMatchesQueryPredicate(""));

        // Null input should throw an exception
        assertThrows(NullPointerException.class, () -> new PersonMatchesQueryPredicate(null));
    }

    @Test
    public void test_fieldsDoNotMatchQuery_returnsFalse() {
        // Query matches email and address, but does not match name, tags or assets
        predicate = new PersonMatchesQueryPredicate("a");
        assertFalse(predicate.test(new PersonBuilder().withName("Zed")
                                                      .withEmail("alice@email.com")
                                                      .withAddress("Main Street")
                                                      .withTags("friends")
                                                      .withAssets("screwdriver")
                                                      .build()));

        // Query matches phone, but does not match name, tags or assets
        predicate = new PersonMatchesQueryPredicate("1");
        assertFalse(predicate.test(new PersonBuilder().withName("Zed")
                                                      .withPhone("12345")
                                                      .withTags("friends")
                                                      .withAssets("screwdriver")
                                                      .build()));
    }

    @Test
    public void test_nameDoesNotMatchQuery_returnsFalse() {
        // Only one matching word
        predicate = new PersonMatchesQueryPredicate("Bob Carol");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Non-matching word
        predicate = new PersonMatchesQueryPredicate("Carol");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_tagMatchesQuery_returnsTrue() {
        // Exact string match
        predicate = new PersonMatchesQueryPredicate("friends");
        assertTrue(predicate.test(new PersonBuilder().withTags("friends").build()));

        // Partial match
        predicate = new PersonMatchesQueryPredicate("coll");
        assertTrue(predicate.test(new PersonBuilder().withTags("colleagues").build()));

        // Mixed-case match
        predicate = new PersonMatchesQueryPredicate("cOllEaGuEs");
        assertTrue(predicate.test(new PersonBuilder().withTags("colleagues").build()));

        // Short query, long tag
        predicate = new PersonMatchesQueryPredicate("a");
        assertTrue(predicate.test(new PersonBuilder().withTags("colleagues").build()));
    }

    @Test
    public void test_tagDoesNotMatchQuery_returnsFalse() {
        // Non-matching string
        predicate = new PersonMatchesQueryPredicate("femily");
        assertFalse(predicate.test(new PersonBuilder().withTags("family").build()));
    }

    @Test
    public void test_assetMatchesQuery_returnsTrue() {
        // Exact string match
        predicate = new PersonMatchesQueryPredicate("hammer");
        assertTrue(predicate.test(new PersonBuilder().withAssets("hammer").build()));

        // Partial string match
        predicate = new PersonMatchesQueryPredicate("driver");
        assertTrue(predicate.test(new PersonBuilder().withAssets("screwdriver").build()));

        // Mixed-case match
        predicate = new PersonMatchesQueryPredicate("sCrEwDriVer");
        assertTrue(predicate.test(new PersonBuilder().withAssets("screwdriver").build()));

        // Short query, long asset
        predicate = new PersonMatchesQueryPredicate("a");
        assertTrue(predicate.test(new PersonBuilder().withAssets("hammer").build()));

        // Query without whitespace
        predicate = new PersonMatchesQueryPredicate("rsc");
        assertTrue(predicate.test(new PersonBuilder().withAssets("hammer screw").build()));
    }

    @Test
    public void test_assetDoesNotMatchQuery_returnsFalse() {
        // Non-matching query
        predicate = new PersonMatchesQueryPredicate("halmet");
        assertFalse(predicate.test(new PersonBuilder().withAssets("helmet").build()));

        // Search query longer than field size
        predicate = new PersonMatchesQueryPredicate("helmet screwdriver");
        assertFalse(predicate.test(new PersonBuilder().withAssets("helmet").build()));
    }

}
