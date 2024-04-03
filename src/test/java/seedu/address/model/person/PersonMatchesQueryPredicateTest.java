package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonMatchesQueryPredicateTest {

    @Test
    public void equals() {
        String firstPredicateQuery = "first";
        String secondPredicateQuery = "first second";

        PersonMatchesQueryPredicate firstPredicate =
                new PersonMatchesQueryPredicate(firstPredicateQuery);
        PersonMatchesQueryPredicate secondPredicate =
                new PersonMatchesQueryPredicate(secondPredicateQuery);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        PersonMatchesQueryPredicate firstPredicateCopy =
                new PersonMatchesQueryPredicate(firstPredicateQuery);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        PersonMatchesQueryPredicate predicate =
                new PersonMatchesQueryPredicate("Alice");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new PersonMatchesQueryPredicate("Alice Bob");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new PersonMatchesQueryPredicate("Bob Carol");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new PersonMatchesQueryPredicate("aLIce bOB");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Short query, long name
        predicate = new PersonMatchesQueryPredicate("y");
        assertTrue(predicate.test(new PersonBuilder().withName("Alex Yeoh The Fifth").build()));

        // Query without whitespace
        predicate = new PersonMatchesQueryPredicate("AlexYeoh");
        assertTrue(predicate.test(new PersonBuilder().withName("Alex Yeoh").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonMatchesQueryPredicate predicate = new PersonMatchesQueryPredicate("");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new PersonMatchesQueryPredicate("Carol");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name, tags or assets
        predicate = new PersonMatchesQueryPredicate("12345 alice@email.com Main Street");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        PersonMatchesQueryPredicate predicate =
                new PersonMatchesQueryPredicate("friends");
        assertTrue(predicate.test(new PersonBuilder().withTags("friends").build()));

        // Multiple keywords
        predicate = new PersonMatchesQueryPredicate("friends colleagues");
        assertTrue(predicate.test(new PersonBuilder().withTags("colleagues").build()));

        // Mixed-case keywords
        predicate = new PersonMatchesQueryPredicate("fRieNdS cOllEaGuEs");
        assertTrue(predicate.test(new PersonBuilder().withTags("colleagues").build()));

        // Short query, long tag
        predicate = new PersonMatchesQueryPredicate("a");
        assertTrue(predicate.test(new PersonBuilder().withTags("colleagues").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonMatchesQueryPredicate predicate = new PersonMatchesQueryPredicate("");
        assertFalse(predicate.test(new PersonBuilder().withTags("friends").build()));

        // Non-matching keyword
        predicate = new PersonMatchesQueryPredicate("friends colleagues");
        assertFalse(predicate.test(new PersonBuilder().withTags("family").build()));

        // Keywords match phone, email and address, but does not match name, tags or assets
        predicate = new PersonMatchesQueryPredicate("12345 alice@email.com Main Street");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withTags("friends").build()));
    }

    @Test
    public void test_assetContainsKeywords_returnsTrue() {
        // One keyword
        PersonMatchesQueryPredicate predicate =
                new PersonMatchesQueryPredicate("hammer");
        assertTrue(predicate.test(new PersonBuilder().withAssets("hammer").build()));

        // Multiple keywords
        predicate = new PersonMatchesQueryPredicate("hammer screwdriver");
        assertTrue(predicate.test(new PersonBuilder().withAssets("screwdriver").build()));

        // Mixed-case keywords
        predicate = new PersonMatchesQueryPredicate("hAmMeR sCrEwDriVer");
        assertTrue(predicate.test(new PersonBuilder().withAssets("screwdriver").build()));

        // Short query, long asset
        predicate = new PersonMatchesQueryPredicate("a");
        assertTrue(predicate.test(new PersonBuilder().withAssets("hammer").build()));
    }

    @Test
    public void test_assetDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonMatchesQueryPredicate predicate = new PersonMatchesQueryPredicate("");
        assertFalse(predicate.test(new PersonBuilder().withAssets("hammer").build()));

        // Non-matching keyword
        predicate = new PersonMatchesQueryPredicate("helmet gloves");
        assertFalse(predicate.test(new PersonBuilder().withAssets("hammer").build()));

        // Keywords match phone, email and address, but does not match name, tags or assets
        predicate = new PersonMatchesQueryPredicate("12345 alice@email.com Main Street");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice")
                                   .withPhone("12345")
                                   .withEmail("alice@email.com")
                                   .withAddress("Main Street")
                                   .withTags("friends")
                                   .withAssets("hammer")
                                   .build()));
    }

}
