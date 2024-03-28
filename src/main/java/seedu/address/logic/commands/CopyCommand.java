package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.person.fields.Address.PREFIX_ADDRESS;
import static seedu.address.model.person.fields.Assets.PREFIX_ASSET;
import static seedu.address.model.person.fields.Email.PREFIX_EMAIL;
import static seedu.address.model.person.fields.Name.PREFIX_NAME;
import static seedu.address.model.person.fields.Phone.PREFIX_PHONE;
import static seedu.address.model.person.fields.Tags.PREFIX_TAG;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.util.ArgumentMultimap;
import seedu.address.logic.util.ArgumentTokenizer;
import seedu.address.logic.util.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Copies the details of a person in the address book.
 */
public class CopyCommand extends Command {

    public static final String COMMAND_WORD = "copy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Copies one detail of the prefix identified\n"
        + "Parameters: "
        + "[" + PREFIX_NAME + "] "
        + "[" + PREFIX_PHONE + "] "
        + "[" + PREFIX_EMAIL + "] "
        + "[" + PREFIX_ADDRESS + "] "
        + "[" + PREFIX_TAG + "] "
        + "[" + PREFIX_ASSET + "]\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_NAME + " ";

    public static final String MESSAGE_NO_PARAM = "One field to copy must be provided.";
    public static final String MESSAGE_EXTRA_PARAM = "Only one field can be copied.";

    private final Index index;
    private final boolean[] info;

    /**
     * @param index of the person in the filtered person list to copy
     * @param info list of details to copy to clipboard
     */
    public CopyCommand(Index index, boolean[] info) {
        requireNonNull(index);

        this.index = index;
        this.info = info;
    }

    @Override
    public String execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToCopy = lastShownList.get(index.getZeroBased());
        String personDetails = copyToClipboard(personToCopy, info);

        return String.format(Messages.MESSAGE_COPIED, personDetails);
    }

    /**
     * Copies the details of {@code personToCopy} to clipboard
     */
    private static String copyToClipboard(Person personToCopy, boolean[] info) {
        requireNonNull(personToCopy);

        if (info[0]) {
            return personToCopy.getName().toString();
        }
        if (info[1]) {
            return personToCopy.getPhone().toString();
        }
        if (info[2]) {
            return personToCopy.getEmail().toString();
        }
        if (info[3]) {
            return personToCopy.getAddress().toString();
        }
        if (info[4]) {
            return personToCopy.getTags().toString();
        }
        if (info[5]) {
            return personToCopy.getAssets().toString();
        }

        throw new IllegalArgumentException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    /**
     * Parses the given {@code String} of arguments in the context of the CopyCommand
     * and returns an CopyCommand object for execution.
     * @throws IllegalArgumentException if the user input does not conform the expected format
     */
    public static CopyCommand of(String args) throws IllegalArgumentException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_TAG, PREFIX_ASSET);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalArgumentException ie) {
            throw new IllegalArgumentException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), ie);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        boolean[] info = new boolean[6];
        int totalParams = 0;

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            info[0] = true;
            totalParams++;
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            info[1] = true;
            totalParams++;
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            info[2] = true;
            totalParams++;
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            info[3] = true;
            totalParams++;
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            info[4] = true;
            totalParams++;
        }
        if (argMultimap.getValue(PREFIX_ASSET).isPresent()) {
            info[5] = true;
            totalParams++;
        }

        if (totalParams == 0) {
            throw new IllegalArgumentException(MESSAGE_NO_PARAM);
        } else if (totalParams > 1) {
            throw new IllegalArgumentException(MESSAGE_EXTRA_PARAM);
        }

        return new CopyCommand(index, info);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CopyCommand)) {
            return false;
        }

        CopyCommand otherCopyCommand = (CopyCommand) other;
        return index.equals(otherCopyCommand.index);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("index", index)
            .toString();
    }
}
