package seedu.address.model.asset;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents an asset in the address book.
 * Guarantees: immutable.
 */
public class Asset {

    public static final String MESSAGE_CONSTRAINTS = "Assets must be entered in the format NAME[#ID][@LOCATION]";
    private static final String VALIDATION_REGEX = "\\S[^#@]*(#[^#@]+)?(@[^#@]+)?";

    private final String assetName;
    private final String assetId;
    private final String assetLocation;

    private Asset(String assetName, String assetId, String assetLocation) {
        requireAllNonNull(assetName, assetId, assetLocation);
        this.assetName = assetName;
        this.assetId = assetId;
        this.assetLocation = assetLocation;
    }

    @JsonValue
    public String get() {
        return toString();
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    private static boolean isValid(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Parses a {@code String} of format {@code NAME[#ID][@LOCATION]} into a {@code Asset}.
     * Leading and trailing whitespaces of each field will be trimmed.
     *
     * @throws IllegalArgumentException if the given {@code name} is invalid.
     */
    public static Asset of(String assetDescription) throws IllegalArgumentException {
        requireNonNull(assetDescription);
        checkArgument(isValid(assetDescription), MESSAGE_CONSTRAINTS);

        String location = "";
        String id = "";

        String trimmedDescription = assetDescription.trim();
        String[] splitByAt = trimmedDescription.split("@", 2);
        if (splitByAt.length == 2) {
            location = splitByAt[1].trim();
        }
        String[] splitByHash = splitByAt[0].split("#", 2);
        if (splitByHash.length == 2) {
            id = splitByHash[1].trim();
        }
        String name = splitByHash[0].trim();

        return new Asset(name, id, location);
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
        return assetName.equals(otherAsset.assetName)
                && assetId.equals(otherAsset.assetId)
                && assetLocation.equals(otherAsset.assetLocation);
    }

    @Override
    public int hashCode() {
        return assetName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        StringBuilder res = new StringBuilder(assetName);
        if (!assetId.isEmpty()) {
            res.append("#").append(assetId);
        }
        if (!assetLocation.isEmpty()) {
            res.append("@").append(assetLocation);
        }
        return res.toString();
    }

}
