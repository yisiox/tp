package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name}, {@code Tags} or {@code Assets} matches any of the keywords given.
 */
public class PersonMatchesKeywordsPredicate implements Predicate<Person> {

    private final List<String> keywords;

    public PersonMatchesKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> fuzzyMatch(person.getName().toString(), keyword) ||
                        person.getTags().stream().anyMatch(tag -> fuzzyMatch(tag.get(), keyword)) ||
                        person.getAssets().stream().anyMatch(asset -> fuzzyMatch(asset.get(), keyword)));
    }


    private int calculateLevenshteinDistance(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        int[] costs = new int[b.length() + 1];

        for (int j = 0; j < costs.length; j++) {
            costs[j] = j;
        }

        for (int i = 1; i <= a.length(); i++) {
            costs[0] = i;
            int nw = i - 1;

            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }

        return costs[b.length()];
    }

    private boolean fuzzyMatch(String text, String keyword) {
        text = text.toLowerCase();
        keyword = keyword.toLowerCase();

        // Split the text into words
        String[] words = text.split("\\s+");

        for (String word : words) {
            int levenshteinDistance = calculateLevenshteinDistance(word, keyword);

            // Calculate the similarity based on the length of the longer string (word or keyword)
            int longerLength = Math.max(word.length(), keyword.length());
            double similarity = 1.0 - (double) levenshteinDistance / longerLength;

            if (similarity >= 0.7) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonMatchesKeywordsPredicate)) {
            return false;
        }

        PersonMatchesKeywordsPredicate otherPersonMatchesKeywordsPredicate = (PersonMatchesKeywordsPredicate) other;
        return keywords.equals(otherPersonMatchesKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }

}
