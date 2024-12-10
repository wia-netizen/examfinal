package agenda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

/**
 * Description : An agenda that stores events
 */
public class Agenda {
    private List<Event> events;
    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */
    public Agenda() {
        this.events = new ArrayList<>();
    }
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Computes the events that occur on a given day
     *
     * @param day the day toi test
     * @return a list of events that occur on that day
     */
    public List<Event> eventsInDay(LocalDate day) {
        List<Event> result = new ArrayList<>();
        for (Event event : events) {
            if (event.isInDay(day)) {
                result.add(event);
            }
        }
        return result;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Agenda:\n");
        for (Event event : events) {
            sb.append(event).append("\n");
        }
        return sb.toString();
    }
}
