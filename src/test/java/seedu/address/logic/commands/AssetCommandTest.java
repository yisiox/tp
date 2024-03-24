package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AssetCommand.MESSAGE_INVALID_ASSET_NAME;
import static seedu.address.logic.commands.AssetCommand.MESSAGE_NOT_EDITED;
import static seedu.address.logic.commands.AssetCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertParseFailure;
import static seedu.address.model.asset.Asset.MESSAGE_CONSTRAINTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.asset.Asset;
import seedu.address.model.person.Person;
import seedu.address.testutil.AssetBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class AssetCommandTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssetCommand.MESSAGE_USAGE);
    private static final String MESSAGE_CONSTRAINT_NAME = "Names should only contain alphanumeric characters and "
            + "spaces, and it should not be blank";
    private final Asset asset1 = Asset.of("laptop");
    private final Asset asset2 = Asset.of("Desktop");

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validEdit_success() throws CommandException {
        Person personWithAsset = new PersonBuilder().withAssets(asset1.get()).build();
        model.addPerson(personWithAsset);
        Asset editedAsset = new AssetBuilder().build();
        AssetCommand editCommand = new AssetCommand(asset1, editedAsset);

        String expectedMessage = String.format(MESSAGE_SUCCESS, editedAsset);

        assertEquals(expectedMessage, editCommand.execute(model));
    }


    @Test
    public void execute_notEdited_throwsCommandException() {
        assertThrows(CommandException.class, MESSAGE_NOT_EDITED, () ->
                new AssetCommand(asset1, asset1).execute(model));
    }

    @Test
    public void execute_invalidAsset_throwsCommandException() {
        Person personWithAsset = new PersonBuilder().withAssets(asset1.get()).build();
        model.addPerson(personWithAsset);
        Asset invalidEditedAsset = new AssetBuilder().build();
        AssetCommand editCommand = new AssetCommand(invalidEditedAsset, asset1);

        assertThrows(CommandException.class, MESSAGE_INVALID_ASSET_NAME, () ->
                editCommand.execute(model));
    }

    @Test
    public void execute_emptyDescriptor_throwsCommandException() {
        assertThrows(IllegalArgumentException.class, MESSAGE_CONSTRAINTS, () ->
                new AssetCommand(Asset.of(""), asset1));
    }

    @Test
    public void of_invalidInput_failure() {
        // missing field
        assertParseFailure(AssetCommand::of, "Laptop", MESSAGE_INVALID_FORMAT);
        // missing field
        assertParseFailure(AssetCommand::of, "", MESSAGE_INVALID_FORMAT);
        // null
        assertThrows(NullPointerException.class, () -> AssetCommand.of(null));
        // unedited
        assertThrows(IllegalArgumentException.class, () -> AssetCommand.of("Laptop Laptop"));
        // only alphanum allowed for name
        assertThrows(IllegalArgumentException.class, () -> AssetCommand.of("Laptop \uD83D\uDC4D"));
    }

    @Test
    public void of_validInput_success() {
        assertDoesNotThrow(() -> AssetCommand.of(" old/aircon new/desktop"));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        AssetCommand editCommand1 = new AssetCommand(asset1, asset2);
        AssetCommand editCommand2 = new AssetCommand(asset1, asset2);
        assertEquals(editCommand1, editCommand2);
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        AssetCommand editCommand1 = new AssetCommand(asset1, asset2);
        AssetCommand editCommand2 = new AssetCommand(asset2, asset1);
        assertNotEquals(editCommand1, editCommand2);
    }

    @Test
    public void equals_differentObject_returnsFalse() {
        AssetCommand editCommand = new AssetCommand(asset1, asset2);
        assertNotEquals(editCommand, new Object());
    }

}
