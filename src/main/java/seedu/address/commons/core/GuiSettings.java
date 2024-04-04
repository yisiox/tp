package seedu.address.commons.core;

import java.awt.Point;
import java.io.Serializable;
import java.util.Objects;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 * A Serializable class that contains the GUI settings.
 * Guarantees: immutable.
 */
public class GuiSettings implements Serializable {

    private static final double DEFAULT_SCREEN_HEIGHT_SCALE = 0.9;
    private static final double DEFAULT_SCREEN_WIDTH_SCALE = 0.5;
    private static final double DEFAULT_HEIGHT = 600;
    private static final double DEFAULT_WIDTH = 740;
    private static final double DEFAULT_SPLIT_PANE_DIVIDER_POSITION = 0.75;

    private double windowWidth;
    private double windowHeight;
    private Point windowCoordinates = null; //null represent no coordinates
    private boolean isMaximized = false;
    private final double splitPaneDividerPosition;

    /**
     * Constructs a {@code GuiSettings} with the default parameters.
     */
    public GuiSettings() {
        try {
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            windowHeight = primaryScreenBounds.getHeight() * DEFAULT_SCREEN_HEIGHT_SCALE;
            windowWidth = primaryScreenBounds.getWidth() * DEFAULT_SCREEN_WIDTH_SCALE;
        } catch (ExceptionInInitializerError | NoClassDefFoundError e) { // No screen found
            windowWidth = DEFAULT_WIDTH;
            windowHeight = DEFAULT_HEIGHT;
        }
        splitPaneDividerPosition = DEFAULT_SPLIT_PANE_DIVIDER_POSITION;
    }

    /**
     * Constructs a {@code GuiSettings} with the specified parameters.
     *
     * @param windowWidth the window's width.
     * @param windowHeight the window's height.
     * @param xPosition the window's x-coordinate on the screen.
     * @param yPosition the window's y-coordinate on the screen.
     * @param isMaximized whether the window is maximized.
     * @param splitPaneDividerPosition the height of the TextArea resultDisplay.
     */
    public GuiSettings(double windowWidth, double windowHeight, int xPosition, int yPosition,
                       boolean isMaximized, double splitPaneDividerPosition) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        windowCoordinates = new Point(xPosition, yPosition);
        this.isMaximized = isMaximized;
        this.splitPaneDividerPosition = splitPaneDividerPosition;
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

    public boolean getIsMaximized() {
        return isMaximized;
    }

    public double getSplitPaneDividerPosition() {
        return splitPaneDividerPosition;
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
                && isMaximized == otherGuiSettings.isMaximized
                && splitPaneDividerPosition == otherGuiSettings.splitPaneDividerPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(windowWidth, windowHeight, windowCoordinates, isMaximized, splitPaneDividerPosition);
    }

}
