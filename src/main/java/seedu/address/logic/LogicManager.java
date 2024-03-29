package seedu.address.logic;

import java.nio.file.Path;
import java.util.Stack;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.util.AddressBookParser;
import seedu.address.logic.util.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.storage.Storage;
import seedu.address.storage.exceptions.StorageException;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;

    private final Stack<String> commandTextHistory = new Stack<>();
    private final Stack<String> commandTextFuture = new Stack<>();

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
    }

    @Override
    public String execute(String commandText) throws CommandException, ParseException, StorageException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        Command command = AddressBookParser.parseCommand(commandText);
        String commandResult = command.execute(model);
        storage.saveAddressBook(model.getAddressBook());

        // keep track of valid commands
        commandTextHistory.push(commandText);
        commandTextFuture.clear();
        logger.info("\"" + commandText + "\" pushed to stack, commandTextFuture stack cleared");

        return commandResult;
    }

    @Override
    public String getPreviousCommandText() {
        if (commandTextHistory.empty()) {
            return "";
        }

        String commandText = commandTextHistory.pop();
        commandTextFuture.push(commandText);
        return commandText;
    }

    @Override
    public String getNextCommandText() {
        if (commandTextFuture.empty()) {
            return "";
        }

        String commandText = commandTextFuture.pop();
        commandTextHistory.push(commandText);
        return commandText;
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

}
