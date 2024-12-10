package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class RecurrentEvent extends Event {
    private Repetition repetition;

    /**
     * Constructeur avec répétition infinie.
     */
    public RecurrentEvent(String title, LocalDateTime start, Duration duration, ChronoUnit unit) {
        super(title, start, duration);
        this.repetition = new Repetition(unit);
    }

    /**
     * Constructeur avec une date de terminaison.
     */
    public RecurrentEvent(String title, LocalDateTime start, Duration duration, ChronoUnit unit, LocalDate terminationDate) {
        super(title, start, duration);
        this.repetition = new Repetition(unit);
        this.repetition.setTermination(new Termination(start.toLocalDate(), unit, terminationDate));
    }

    /**
     * Constructeur avec un nombre d'occurrences.
     */
    public RecurrentEvent(String title, LocalDateTime start, Duration duration, ChronoUnit unit, int occurrences) {
        super(title, start, duration);
        this.repetition = new Repetition(unit);
        this.repetition.setTermination(new Termination(start.toLocalDate(), unit, occurrences));
    }
}
