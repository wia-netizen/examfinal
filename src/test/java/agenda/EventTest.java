package agenda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    LocalDateTime startDateTime = LocalDateTime.of(2020, 11, 1, 22, 30);
    LocalDate endTerminationDate = LocalDate.of(2021, 1, 5);
    Duration duration = Duration.ofMinutes(120);

    Event weeklyEvent;
    Event exceptionEvent;

    @BeforeEach
    void setUp() {
        // Création de l'événement avec une date de début et une répétition hebdomadaire jusqu'à la date de terminaison
        weeklyEvent = new Event("Event with termination by date", startDateTime, duration);
        weeklyEvent.setRepetition(ChronoUnit.WEEKS);
        weeklyEvent.setTermination(endTerminationDate);

        // Création d'un événement avec une exception à certaines dates
        exceptionEvent = new Event("Event with exceptions", startDateTime, duration);
        exceptionEvent.setRepetition(ChronoUnit.WEEKS);
        exceptionEvent.addException(LocalDate.from(startDateTime.plusWeeks(2)));
        exceptionEvent.addException(LocalDate.from(startDateTime.plusWeeks(4)));
    }

    @Test
    public void testNumberOfOccurrencesFromTerminationDate() {
        // Vérifier si le nombre d'occurrences jusqu'à la date de terminaison est correct
        assertEquals(9, weeklyEvent.getNumberOfOccurrences(), "Le nombre d'occurrences jusqu'à la date de terminaison doit être 10");
    }



    @Test
    public void testIsInTerminationDay() {
        // Vérifier si la date finale est bien incluse dans les événements.
        assertTrue(weeklyEvent.isInDay(LocalDate.of(2021, 1, 3)), "L'événement doit avoir lieu le jour de la date de terminaison");
    }

    @Test
    public void testOccurrencesOnStartDay() {
        // Vérifier si l'événement est valide le jour de son démarrage.
        assertTrue(weeklyEvent.isInDay(LocalDate.of(2020, 11, 1)), "L'événement doit avoir lieu dans son premier jour");
    }

    @Test
    public void testExceptionsBehavior() {
        // Vérifier si les exceptions fonctionnent correctement avec des semaines spécifiques.
        assertFalse(exceptionEvent.isInDay(LocalDate.of(2020, 11, 15)), "L'événement ne doit pas se produire le 15 novembre");
        assertFalse(exceptionEvent.isInDay(LocalDate.of(2020, 11, 29)), "L'événement ne doit pas se produire le 29 novembre");
        assertTrue(exceptionEvent.isInDay(LocalDate.of(2020, 11, 22)), "L'événement doit se produire le 22 novembre");
    }

    @Test
    public void testRepetitionEveryWeeksBehavior() {
        // Vérifier si l'événement suit bien sa logique répétée toutes les semaines.
        assertTrue(weeklyEvent.isInDay(LocalDate.of(2020, 11, 8)), "L'événement doit avoir lieu une semaine après la date de début");
        assertTrue(weeklyEvent.isInDay(LocalDate.of(2020, 11, 15)), "L'événement doit avoir lieu deux semaines après la date de début");
    }
}
