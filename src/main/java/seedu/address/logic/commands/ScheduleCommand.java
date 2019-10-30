package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DAYS;

import java.time.LocalTime;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.day.ActivityWithTime;
import seedu.address.model.day.Day;
import seedu.address.model.itineraryitem.activity.Activity;

/**
 * Schedules an activity to a day.
 */
public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + ": Schedule the activity identified "
            + "by the index number used in the displayed activity list "
            + "to a day.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_START_TIME + "START_TIME "
            + PREFIX_END_TIME + "END_TIME "
            + PREFIX_DAY + "DAY_INDEX "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_START_TIME + "1100 "
            + PREFIX_END_TIME + "1300 "
            + PREFIX_DAY + "2 ";

    public static final String MESSAGE_SCHEDULE_ACTIVITY_SUCCESS = "Activity scheduled to day %d";
    public static final String MESSAGE_DUPLICATE_DAY = "This day already exists in the planner.";

    private final Index activityIndex;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final Index dayIndex;

    /**
     * Creates an AddActivityCommand to add the specified {@Activity}
     */
    public ScheduleCommand(Index activityIndex, LocalTime startTime, LocalTime endTime, Index dayIndex) {
        requireAllNonNull(activityIndex, startTime, endTime, dayIndex);
        this.activityIndex = activityIndex;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayIndex = dayIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Day> lastShownDays = model.getFilteredItinerary();
        List<Activity> lastShownActivities = model.getFilteredActivityList();

        if (dayIndex.getZeroBased() >= lastShownDays.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DAY_DISPLAYED_INDEX);
        }
        if (activityIndex.getZeroBased() >= lastShownActivities.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
        }

        Day dayToEdit = lastShownDays.get(dayIndex.getZeroBased());
        Activity activityToSchedule = lastShownActivities.get(activityIndex.getZeroBased());
        ActivityWithTime activityWithTimeToAdd = new ActivityWithTime(activityToSchedule, startTime, endTime);

        model.scheduleActivity(dayToEdit, activityWithTimeToAdd);

        model.updateFilteredItinerary(PREDICATE_SHOW_ALL_DAYS);
        return new CommandResult(String.format(MESSAGE_SCHEDULE_ACTIVITY_SUCCESS, dayIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScheduleCommand // instanceof handles nulls
                && this.activityIndex.equals(((ScheduleCommand) other).activityIndex)
                && this.startTime.equals((((ScheduleCommand) other).startTime))
                && this.endTime.equals(((ScheduleCommand) other).endTime));
    }
}
