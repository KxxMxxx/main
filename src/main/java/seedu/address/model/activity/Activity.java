package seedu.address.model.activity;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an Activity in the trip planner.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Activity {

    //Identity fields
    private final Description description;

    //Data fields
    private final Location location;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Activity(Description description, Location location, Set<Tag> tags) {
        requireAllNonNull(description, location, tags);
        this.description = description;
        this.location = location;
        this.tags.addAll(tags);
    }

    public Description getDescription() {
        return description;
    }

    public Location getLocation() {
        return location;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }


    /**
     * Returns true if both activities have the same identity and data fields.
     * This defines a stronger notion of equality between two activities.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Activity)) {
            return false;
        }

        Activity otherActivity = (Activity) other;
        return otherActivity.getDescription().equals(getDescription())
                && otherActivity.getLocation().equals(getLocation())
                && otherActivity.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, location, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append(" Location: ")
                .append(getLocation())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
