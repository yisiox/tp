package seedu.address.commons.core;

import java.awt.Point;
import java.io.Serializable;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * A Serializable class that contains the GUI settings.
 * Guarantees: immutable.
 */
public class GuiSettings implements Serializable {

    private static final double DEFAULT_HEIGHT = 400;
    private static final double DEFAULT_WIDTH = 450;

    private static final double DEFAULT_SPLIT_POSITION = 0.75;

    private final double windowWidth;
    private final double windowHeight;
    private final Point windowCoordinates;
    private final boolean isMaximized;

    private final double splitPosition;

    /**
     * Constructs a {@code GuiSettings} with the default height, width and position.
     */
    public GuiSettings() {
        windowWidth = DEFAULT_WIDTH;
        windowHeight = DEFAULT_HEIGHT;
        splitPosition = DEFAULT_SPLIT_POSITION;
        windowCoordinates = null; // null represent no coordinates
        isMaximized = false;
    }

    /**
     * Constructs a {@code GuiSettings} with the specified height, width and position.
     */
    public GuiSettings(double windowWidth, double windowHeight, int xPosition, int yPosition, double splitPosition, boolean isMaximized) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.splitPosition = splitPosition;
        windowCoordinates = new Point(xPosition, yPosition);
        this.isMaximized = isMaximized;
    }

    public double getWindowWidth() {
        return windowWidth;
    }

    public double getWindowHeight() {
        return windowHeight;
    }

    public Point getWindowCoordinates() {
        return windowCoordinates != null ? new Point(windowCoordinates) : null;
    }

    public double getSplitPosition() {
        return splitPosition;
    }

    public boolean getIsMaximized() {
        return isMaximized;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GuiSettings)) {
            return false;
        }

        GuiSettings otherGuiSettings = (GuiSettings) other;
        return windowWidth == otherGuiSettings.windowWidth
                && windowHeight == otherGuiSettings.windowHeight
                && Objects.equals(windowCoordinates, otherGuiSettings.windowCoordinates)
                && splitPosition == otherGuiSettings.splitPosition
                && isMaximized == otherGuiSettings.isMaximized;
    }

    @Override
    public int hashCode() {
        return Objects.hash(windowWidth, windowHeight, windowCoordinates, splitPosition, isMaximized);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("windowWidth", windowWidth)
                .add("windowHeight", windowHeight)
                .add("windowCoordinates", windowCoordinates)
                .add("splitHeight", splitPosition)
                .add("isMaximized", isMaximized)
                .toString();
    }

}
