package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.UnscheduleCommand;
import seedu.address.logic.commands.UnscheduleTimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.day.time.TimeInHalfHour;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class UnscheduleCommandParser implements Parser<UnscheduleCommand> {

    private static final Pattern UNSCHEDULE_COMMAND_FORMAT = Pattern.compile("(?<type>\\S+)(?<arguments>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnscheduleCommand parse(String args) throws ParseException {
        final Matcher matcher = UNSCHEDULE_COMMAND_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String type = matcher.group("type");
        final String arguments = matcher.group("arguments");

        switch(type) {
        case UnscheduleTimeCommand.SECOND_COMMAND_WORD:
            return parseUnscheduleTime(arguments);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddActivityCommand for a Activity
     * and returns an AddActivityCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    private UnscheduleTimeCommand parseUnscheduleTime(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DAY, PREFIX_START_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_DAY, PREFIX_START_TIME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnscheduleCommand.MESSAGE_USAGE));
        }

        Index dayIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_DAY).get());
        TimeInHalfHour startTime = ParserUtil.parseTimeInHalfHour(argMultimap.getValue(PREFIX_START_TIME).get());
        return new UnscheduleTimeCommand(dayIndex, startTime);
    }


}
