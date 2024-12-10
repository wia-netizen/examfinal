package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Event {

    /**
     * The myTitle of this event
     */
    private String myTitle;

    /**
     * The starting time of the event
     */
    private LocalDateTime myStart;

    /**
     * The durarion of the event
     */
    private Duration myDuration;
    private ChronoUnit repetitionUnit;
    private LocalDate terminationDate;
    private Integer numberOfOccurrences;
    private List<LocalDate> exceptions;

    /**
     * Constructs an event
     *
     * @param title    the title of this event
     * @param start    the start time of this event
     * @param duration the duration of this event
     */
    public Event(String title, LocalDateTime start, Duration duration) {
        this.myTitle = title;
        this.myStart = start;
        this.myDuration = duration;
        this.exceptions = new ArrayList<>();
    }

    public void setRepetition(ChronoUnit unit) {
        this.repetitionUnit = unit;
    }

    public void setTermination(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
    }

    public void setTermination(int occurrences) {
        this.numberOfOccurrences = occurrences;
    }

    public void addException(LocalDate exceptionDate) {
        exceptions.add(exceptionDate);
    }

    public boolean isInDay(LocalDate day) {
        if (exceptions.contains(day)) {
            return false;
        }

        LocalDateTime eventEnd = myStart.plus(myDuration);

        if (!myStart.toLocalDate().isAfter(day) && !eventEnd.toLocalDate().isBefore(day)) {
            return true;
        }

        if (repetitionUnit != null) {
            LocalDate current = myStart.toLocalDate();
            while (!current.isAfter(day)) {
                if (current.equals(day)) {
                    return true;
                }
                current = current.plus(1, repetitionUnit);
            }
        }

        return false;
    }

    public int getNumberOfOccurrences() {
        if (numberOfOccurrences != null) {
            return numberOfOccurrences;
        }

        if (terminationDate != null && repetitionUnit != null) {
            return (int) ChronoUnit.DAYS.between(myStart.toLocalDate(), terminationDate) / (int) repetitionUnit.getDuration().toDays();
        }

        return 0; // Par défaut
    }


    public LocalDate getTerminationDate() {
        if (terminationDate != null) {
            return terminationDate;
        }

        if (numberOfOccurrences != null && repetitionUnit != null) {
            return myStart.toLocalDate().plus(numberOfOccurrences - 1, repetitionUnit);
        }

        return null;
    }

    @Override
    public String toString() {
        return "Event{title='%s', start=%s, duration=%s}".formatted(myTitle, myStart, myDuration);
    }

    /**
     * Tests if an event occurs on a given day
     *
     * @param aDay the day to test
     * @return true if the event occurs on that day, false otherwise
     */

    /**
     * @return the myTitle
     */
    public String getTitle() {
        return myTitle;
    }

    /**
     * @return the myStart
     */
    public LocalDateTime getStart() {
        return myStart;
    }


    /**
     * @return the myDuration
     */
    public Duration getDuration() {
        return myDuration;
    }
}
