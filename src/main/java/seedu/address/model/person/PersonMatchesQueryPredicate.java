package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name}, {@code Tags} or {@code Assets} matches the search query.
 * The algorithm removes all whitespaces and checks if the query is a substring of a field.
 * The substring matching is case-insensitive.
 */
public class PersonMatchesQueryPredicate implements Predicate<Person> {

    private final String query;

    public PersonMatchesQueryPredicate(String query) {
        this.query = query;
    }

    //@@author rizkidelta
    @Override
    public boolean test(Person person) {
        return substringMatch(person.getName().toString(), query)
               || person.getTags().stream().anyMatch(tag -> substringMatch(tag.get(), query)
               || person.getAssets().stream().anyMatch(asset -> substringMatch(asset.get(), query)));
    }

    //@@author rizkidelta
    private boolean substringMatch(String text, String query) {
        final String whitespaceRegex = "\\s+";
        final String emptyString = "";

        // remove all whitespaces from strings
        text = text.toLowerCase().replaceAll(whitespaceRegex, emptyString);
        query = query.toLowerCase().replaceAll(whitespaceRegex, emptyString);

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

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", query).toString();
    }

}
