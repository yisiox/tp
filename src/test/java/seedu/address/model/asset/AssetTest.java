package seedu.address.model.asset;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AssetTest {

    @Test
    public void of_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Asset.of(null));
    }

    @Test
    public void of_invalidAssetDescription_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Asset.of("")); // empty string
        assertThrows(IllegalArgumentException.class, () -> Asset.of(" ")); // spaces only
        assertThrows(IllegalArgumentException.class, () -> Asset.of("#@")); // empty fields
        assertThrows(IllegalArgumentException.class, () -> Asset.of("#"));
        assertThrows(IllegalArgumentException.class, () -> Asset.of("@"));
        assertThrows(IllegalArgumentException.class, () -> Asset.of("#id@location"));
        assertThrows(IllegalArgumentException.class, () -> Asset.of("name#id@"));
        assertThrows(IllegalArgumentException.class, () -> Asset.of("name@location#id")); // wrong order
        assertThrows(IllegalArgumentException.class, () -> Asset.of("name#id@location#1")); // too many hashes
        assertThrows(IllegalArgumentException.class, () -> Asset.of("name##id"));
        assertThrows(IllegalArgumentException.class, () -> Asset.of("na#me#id"));
        assertThrows(IllegalArgumentException.class, () -> Asset.of("name@@location")); // too many at
        assertThrows(IllegalArgumentException.class, () -> Asset.of("name#@location")); // empty id
    }

    @Test
    public void of_validAssetDescription_success() {
        assertDoesNotThrow(() -> Asset.of("a"));
        assertDoesNotThrow(() -> Asset.of("abc"));
        assertDoesNotThrow(() -> Asset.of("validAsset"));
        assertDoesNotThrow(() -> Asset.of("ValidAsset"));
        assertDoesNotThrow(() -> Asset.of("VALIDAsset"));
        assertDoesNotThrow(() -> Asset.of("name"));
        assertDoesNotThrow(() -> Asset.of("name#id"));
        assertDoesNotThrow(() -> Asset.of("name#id@location"));
        assertDoesNotThrow(() -> Asset.of("name@location"));
        assertDoesNotThrow(() -> Asset.of(":-)"));
    }

    @Test
    public void equals() {
        Asset asset = Asset.of("name#id@location");

        // same object -> returns true
        assertTrue(asset.equals(asset));

        // same values -> returns true
        assertTrue(asset.equals(Asset.of("name#id@location")));

        // same values, no id -> returns true
        assertTrue(Asset.of("name@location").equals(Asset.of("name@location")));

        // same values, no location -> returns true
        assertTrue(Asset.of("name#id").equals(Asset.of("name#id")));

        // different values -> returns false
        assertFalse(asset.equals(Asset.of("NAME#ID@LOCATION")));

        // different number of values -> returns false
        assertFalse(asset.equals(Asset.of("name")));

        // partial match -> returns false
        assertFalse(asset.equals(Asset.of("name#id@LOCATION")));
    }

    @Test
    public void toString_correctStringRepresentation_success() {
        assertEquals(Asset.of("name").toString(), "[ name ]");
        assertEquals(Asset.of("name#id").toString(), "[ name#id ]");
        assertEquals(Asset.of("name@location").toString(), "[ name@location ]");
        assertEquals(Asset.of("name#id@location").toString(), "[ name#id@location ]");
    }

}
