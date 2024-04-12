package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertParseFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonMatchesQueryPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final List<Person> personsList = getTypicalPersons();

    @Test
    public void equals() {
        Predicate<Person> firstPredicate = new PersonPredicateStub(false);
        Predicate<Person> secondPredicate = new PersonPredicateStub(true);

        FindCommand firstFindCommand = new FindCommand(firstPredicate);
        FindCommand secondFindCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstFindCommand.equals(firstFindCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(firstFindCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(firstFindCommand.equals(1));

        // null -> returns false
        assertFalse(firstFindCommand.equals(null));

        // different person -> returns false
        assertFalse(firstFindCommand.equals(secondFindCommand));
    }

    @Test
    public void execute_stubThatReturnsFalse_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        Predicate<Person> predicate = new PersonPredicateStub(false);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(), model.getFilteredPersonList());
    }

    @Test
    public void execute_stubThatReturnsTrue_allPersonsFound() {
        PersonPredicateStub predicate = new PersonPredicateStub(true);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, personsList.size());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(personsList, model.getFilteredPersonList());
    }

    @Test
    public void of_emptyArg_throwsParseException() {
        assertParseFailure(FindCommand::of, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void of_validArgs_returnsFindCommand() {
        // this test depends on the PersonMatchesQueryPredicate class in order to test the factory constructor of()

        // no leading and trailing whitespaces
        FindCommand expectedFindCommand = new FindCommand(new PersonMatchesQueryPredicate("Alice Bob"));
        assertParseSuccess(FindCommand::of, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(FindCommand::of, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    /**
     * Represents a predicate stub class for testing the FindCommand class.
     * Removes the dependency on the PersonMatchesQueryPredicate class.
     */
    private static class PersonPredicateStub implements Predicate<Person> {

        private final boolean returnValue;

        public PersonPredicateStub(boolean returnValue) {
            this.returnValue = returnValue;
        }

        @Override
        public boolean test(Person person) {
            return returnValue;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof PersonPredicateStub)) {
                return false;
            }

            PersonPredicateStub otherFindCommand = (PersonPredicateStub) other;
            return returnValue == otherFindCommand.returnValue;
        }

    }

}
