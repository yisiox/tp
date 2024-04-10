package seedu.address.model.asset;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents an asset in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValid(String)}.
 */
public class Asset {

    public static final String MESSAGE_CONSTRAINTS = "Asset name must not be blank, and should not contain '\\'";
    private static final String VALIDATION_REGEX = "\\s*[^\\s\\\\][^\\\\]*";

    private final String assetName;

    private Asset(String assetName) {
        requireAllNonNull(assetName);
        this.assetName = assetName;
    }

    @JsonValue
    public String get() {
        return assetName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    private static boolean isValid(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Parses a {@code String} of format {@code NAME[#ID]} into a {@code Asset}.
     * Leading and trailing whitespaces of each field will be trimmed.
     *
     * @throws IllegalArgumentException if the given {@code name} is invalid.
     */
    public static Asset of(String assetDescription) throws IllegalArgumentException {
        requireNonNull(assetDescription);
        checkArgument(isValid(assetDescription), MESSAGE_CONSTRAINTS);
        String trimmedName = assetDescription.trim();
        return new Asset(trimmedName);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Asset)) {
            return false;
        }

        Asset otherAsset = (Asset) other;
        return assetName.equals(otherAsset.assetName);
    }

    @Override
    public int hashCode() {
        return assetName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "[" + get() + "]";
    }

}
