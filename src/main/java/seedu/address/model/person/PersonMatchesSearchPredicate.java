package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name}, {@code Tags} or {@code Assets} matches the search query.
 * The algorithm removes all whitespaces and checks if the query is a substring of a field.
 * The substring matching is case-insensitive.
 */
public class PersonMatchesSearchPredicate implements Predicate<Person> {

    private final List<String> keywords;

    public PersonMatchesSearchPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    //@@author rizkidelta
    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> substringMatch(person.getName().toString(), keyword)
                        || person.getTags().stream().anyMatch(tag -> substringMatch(tag.get(), keyword))
                        || person.getAssets().stream().anyMatch(asset -> substringMatch(asset.get(), keyword)));
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
        if (!(other instanceof PersonMatchesSearchPredicate)) {
            return false;
        }

        PersonMatchesSearchPredicate otherPersonMatchesSearchPredicate = (PersonMatchesSearchPredicate) other;
        return keywords.equals(otherPersonMatchesSearchPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }

}
