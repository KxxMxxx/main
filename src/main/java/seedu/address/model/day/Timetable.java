package seedu.address.model.day;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Represents the timetable of a {@code Day}.
 * Guarantees: {@code Timetable} is filled with {@code HalfHour}.
 */
public class Timetable {
    private List<ActivityWithTime> timetable;

    public Timetable() {
        timetable = new ArrayList<>();
    }

    public Timetable(List<ActivityWithTime> activities) {
        timetable = activities;
        timetable.sort(ActivityWithTime::compareTo);
    }

    public List<ActivityWithTime> getActivitiesWithTime() {
        return new ArrayList<>(this.timetable);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Timetable)) {
            return false;
        }

        List<ActivityWithTime> otherActivities = ((Timetable) other).getActivitiesWithTime();
        List<ActivityWithTime> thisActivities = this.getActivitiesWithTime();
        return thisActivities.equals(otherActivities);
    }
}
