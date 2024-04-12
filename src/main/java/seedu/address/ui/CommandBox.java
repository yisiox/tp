package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.CommandHistoryException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.util.exceptions.ParseException;
import seedu.address.storage.exceptions.StorageException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    private static final Logger logger = LogsCenter.getLogger(CommandBox.class);

    private static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;

        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());

        // add listener for key presses
        commandTextField.setOnKeyPressed(this::handleKeyPressed);
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException | StorageException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Handle the event when a key is pressed.
     *
     * @param event the KeyEvent when a key is pressed.
     */
    private void handleKeyPressed(KeyEvent event) {
        String commandText = null;
        try {
            if (event.getCode() == KeyCode.UP) {
                // go up the command history
                logger.info("UP key pressed");
                commandText = commandExecutor.getPreviousCommandText();
            } else if (event.getCode() == KeyCode.DOWN) {
                // go down the command history
                logger.info("DOWN key pressed");
                commandText = commandExecutor.getNextCommandText();
            }
        } catch (CommandHistoryException che) {
            logger.info(che.getMessage());
            event.consume();
            return;
        }

        if (commandText != null) {
            // update the command box and relocate the caret appropriately
            commandTextField.setText(commandText);
            commandTextField.positionCaret(commandTextField.getLength());
            logger.info("commandTextField set to \"" + commandText + "\"");
        }

        event.consume();
    }

}
