package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GuiSettingsTest {

    @Test
    void equals() {
        GuiSettings guiSettings = new GuiSettings();

        assertFalse(guiSettings.equals(null));
        assertTrue(guiSettings.equals(guiSettings));

        GuiSettings otherGuiSettings = new GuiSettings();
        assertTrue(guiSettings.equals(otherGuiSettings));
        assertEquals(guiSettings.hashCode(), otherGuiSettings.hashCode());
    }

}
