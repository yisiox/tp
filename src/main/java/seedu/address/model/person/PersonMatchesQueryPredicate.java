package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name}, {@code Tags} or {@code Assets} matches the search query.
 * The algorithm removes all whitespaces and checks if the query is a substring of a field.
 * The substring matching is case-insensitive.
 */
public class PersonMatchesQueryPredicate implements Predicate<Person> {

    private static final String STRIP_WHITESPACE_REGEX = "\\s+";
    private static final String EMPTY_STRING = "";

    private final String query;

    /**
     * Processes the query string and constructs the object.
     * @param query The query string.
     * @throws NullPointerException If the query string is null.
     * @throws AssertionError If the query string is empty.
     */
    public PersonMatchesQueryPredicate(String query) {
        requireNonNull(query);
        assert !query.isEmpty();
        this.query = processString(query);
    }

    private static String processString(String str) {
        return str.toLowerCase().replaceAll(STRIP_WHITESPACE_REGEX, EMPTY_STRING);
    }

    //@@author rizkidelta
    @Override
    public boolean test(Person person) {
        return doesStringMatchQuery(person.getName().toString(), query)
               || person.getTags().stream().anyMatch(tag -> doesStringMatchQuery(tag.get(), query)
               || person.getAssets().stream().anyMatch(asset -> doesStringMatchQuery(asset.get(), query)));
    }

    //@@author rizkidelta
    private static boolean doesStringMatchQuery(String text, String query) {
        requireNonNull(text);

        // only need to process text, as query is already processed in the constructor
        text = processString(text);
        return text.contains(query);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonMatchesQueryPredicate)) {
            return false;
        }

        PersonMatchesQueryPredicate otherPersonMatchesQueryPredicate = (PersonMatchesQueryPredicate) other;
        return query.equals(otherPersonMatchesQueryPredicate.query);
    }

}
