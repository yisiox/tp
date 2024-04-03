package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.function.Predicate;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonMatchesQueryPredicate;

/**
 * Finds and lists all persons in the address book such that certain fields match the predicate,
 * See {@code PersonMatchesQueryPredicate}.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all persons whose names, assets or tags contains "
            + "the specified query (case-insensitive) and displays them as a list with index numbers.\n"
            + "All whitespaces are ignored.\n"
            + "Parameters: QUERY\n"
            + "Example: " + COMMAND_WORD + " alex";

    private final Predicate<Person> predicate;

    public FindCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public String execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws IllegalArgumentException if the user input does not conform the expected format
     */
    public static FindCommand of(String args) throws IllegalArgumentException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new FindCommand(new PersonMatchesQueryPredicate(trimmedArgs));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

}
