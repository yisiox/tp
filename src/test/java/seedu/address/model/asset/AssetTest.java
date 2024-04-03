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
    }

    @Test
    public void of_validAssetDescription_success() {
        assertDoesNotThrow(() -> Asset.of("a"));
        assertDoesNotThrow(() -> Asset.of("abc"));
        assertDoesNotThrow(() -> Asset.of("validAsset"));
        assertDoesNotThrow(() -> Asset.of("ValidAsset"));
        assertDoesNotThrow(() -> Asset.of("VALIDAsset"));
        assertDoesNotThrow(() -> Asset.of("valid asset"));
        assertDoesNotThrow(() -> Asset.of(" valid asset "));
    }

    @Test
    public void equals() {
        Asset asset = Asset.of("name");

        // same object -> returns true
        assertTrue(asset.equals(asset));

        // same values -> returns true
        assertTrue(asset.equals(Asset.of("name")));
    }

    @Test
    public void toString_correctStringRepresentation_success() {
        assertEquals(Asset.of("name").toString(), "[ name ]");
    }

}
