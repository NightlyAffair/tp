package seedu.address.tasklist.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.tasklist.exception.TaskManagerException;


/**
 * Represents an event task that has a start and end date/time.
 */
public class Event extends Task {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("EEEE, MMM dd yyyy");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("EEEE, MMM dd yyyy, h:mma");
    private final LocalDateTime start;
    private final LocalDateTime end;
    /**
     * Constructs a new Event task.
     *
     * @param name The description of the event.
     * @param start The start date and time of the event.
     * @param end The end date and time of the event.
     * @throws TaskManagerException If the start time is after the end time.
     */
    public Event(String name, LocalDateTime start, LocalDateTime end) throws TaskManagerException {
        super(name);
        if (start.isAfter(end)) {
            throw new TaskManagerException("Event start time must be before end time.");
        }
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a new Event task with completion status.
     *
     * @param name The description of the event.
     * @param start The start date and time of the event.
     * @param end The end date and time of the event.
     * @param isDone The completion status of the event.
     * @throws TaskManagerException If the start time is after the end time.
     */
    public Event(String name, LocalDateTime start, LocalDateTime end, boolean isDone) throws TaskManagerException {
        super(name);
        if (start.isAfter(end)) {
            throw new TaskManagerException("Event start time must be before end time.");
        }
        this.start = start;
        this.end = end;
        this.isDone = isDone;
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time as a LocalDateTime object.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time as a LocalDateTime object.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Checks if the event includes a given date.
     *
     * @param date The date to check.
     * @return True if the event includes the given date, otherwise false.
     */
    public boolean includesDate(LocalDate date) {
        return !date.isBefore(start.toLocalDate()) && !date.isAfter(end.toLocalDate());
    }

    /**
     * Gets the formatted event dates as a string.
     *
     * @return The formatted event dates.
     */
    public String getDates() {
        return "from: " + start.format(DATE_FORMATTER) + ", to: " + end.format(DATE_FORMATTER);
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(OUTPUT_FORMATTER).toLowerCase()
                + " to: " + end.format(OUTPUT_FORMATTER).toLowerCase() + ")";
    }

    /**
     * Compares this event with another object to determine equality.
     * Two events are considered equal if they have the same description,
     * start time, and end time.
     *
     * @param obj The object to compare with this event.
     * @return true if the object is an Event with the same description, start time, and end time; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        Event event = (Event) obj;
        return this.start.equals(event.start) && this.end.equals(event.end);
    }
}
