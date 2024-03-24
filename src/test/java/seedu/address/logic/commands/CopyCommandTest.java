package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.assertParseFailure;
import static seedu.address.logic.commands.CopyCommand.MESSAGE_USAGE;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for CopyCommand.
 */
public class CopyCommandTest {

    private static final String VALID_INDEX = "1";
    private static final String INVALID_INDEX = "a";
    private final Model model = new ModelManager();

    @Test
    public void execute_validCopy1_success() throws CommandException {
        Person personToCopy = new PersonBuilder().withTags("Bob").withAssets("Aircon").build();
        model.addPerson(personToCopy);
        Index index = Index.fromOneBased(1);
        boolean[] info = {true, true, true, true, true, true};
        CopyCommand copyCommand = new CopyCommand(index, info);

        String copiedDetails = "Name: Amy Bee; "
            + "Phone: 85355255; "
            + "Email: amy@gmail.com; "
            + "Address: 123, Jurong West Ave 6, #08-111; "
            + "Tags: [[Bob]]; "
            + "Assets: [[Aircon]]";

        String expectedMessage = String.format(Messages.MESSAGE_COPIED, copiedDetails);
        assertEquals(expectedMessage, copyCommand.execute(model));
    }

    @Test
    public void execute_validCopy2_success() throws CommandException {
        Person personToCopy = new PersonBuilder().withTags("Bob").withAssets("Aircon").build();
        model.addPerson(personToCopy);
        Index index = Index.fromOneBased(1);
        boolean[] info = {false, true, false, true, true, true};
        CopyCommand copyCommand = new CopyCommand(index, info);

        String copiedDetails = "Phone: 85355255; "
            + "Address: 123, Jurong West Ave 6, #08-111; "
            + "Tags: [[Bob]]; "
            + "Assets: [[Aircon]]";

        String expectedMessage = String.format(Messages.MESSAGE_COPIED, copiedDetails);
        assertEquals(expectedMessage, copyCommand.execute(model));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index index = Index.fromOneBased(2);
        boolean[] info = {true, true, true, true, true, true};
        CopyCommand copyCommand = new CopyCommand(index, info);
        assertThrows(CommandException.class, () -> copyCommand.execute(model));
    }

    @Test
    public void execute_noParam_throwsIllegalArgumentException() {
        Index index = Index.fromOneBased(1);
        boolean[] info = {false, false, false, false, false, false};
        CopyCommand copyCommand = new CopyCommand(index, info);
        assertThrows(CommandException.class, () -> copyCommand.execute(model));
    }

    @Test
    public void of_invalidInput_failure() {
        assertParseFailure(CopyCommand::of, "Invalid", String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        assertParseFailure(CopyCommand::of, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        assertThrows(IllegalArgumentException.class, () -> CopyCommand.of("1"));
        assertThrows(IllegalArgumentException.class, () -> CopyCommand.of(INVALID_INDEX));
    }

    @Test
    public void of_validInput_success() {
        assertThrows(IllegalArgumentException.class, () -> CopyCommand.of(VALID_INDEX + " "));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        Index index = Index.fromOneBased(1);
        boolean[] info = {true, true, true, true, true, true};
        CopyCommand copyCommand1 = new CopyCommand(index, info);
        CopyCommand copyCommand2 = new CopyCommand(index, info);
        assertEquals(copyCommand1, copyCommand2);
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        Index index1 = Index.fromOneBased(1);
        Index index2 = Index.fromOneBased(2);
        boolean[] info1 = {true, true, true, true, true, true};
        boolean[] info2 = {true, true, true, true, true, false};
        CopyCommand copyCommand1 = new CopyCommand(index1, info1);
        CopyCommand copyCommand2 = new CopyCommand(index2, info2);
        assertNotEquals(copyCommand1, copyCommand2);
    }

    @Test
    public void equals_differentObject_returnsFalse() {
        Index index = Index.fromOneBased(1);
        boolean[] info = {true, true, true, true, true, true};
        CopyCommand copyCommand = new CopyCommand(index, info);
        assertNotEquals(copyCommand, new Object());
    }
}
