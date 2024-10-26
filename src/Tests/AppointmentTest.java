// Kenneth Dandrow

package Tests;

import Appointment.Appointment;

import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.*;

public class AppointmentTest {

    @Test
    public void testValidAppointment() {
        Appointment appointment = new Appointment("1234567890", new Date(), "Valid description");
        assertEquals("1234567890", appointment.getAppointmentID());
        assertNotNull(appointment.getAppointmentDate());
        assertEquals("Valid description", appointment.getDescription());
    }

    @Test
    public void testInvalidID() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(null, new Date(), "Valid description");
        });
    }

    @Test
    public void testInvalidIDLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345678901", new Date(), "Valid description"); // ID > 10 characters
        });
    }

    @Test
    public void testInvalidDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            // Date in the past (Jan 1, 1970)
            new Appointment("1234567890", new Date(0), "Valid description");
        });
    }

    @Test
    public void testValidFutureDate() {
        // Set a future date
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1); // 1 day in the future
        Date futureDate = cal.getTime();

        Appointment appointment = new Appointment("1234567890", futureDate, "Valid description");
        assertNotNull(appointment.getAppointmentDate());
    }

    @Test
    public void testInvalidDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("1234567890", new Date(), null); // Null description
        });
    }

    @Test
    public void testInvalidDescriptionLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("1234567890", new Date(), "This description is way too long to be valid because it exceeds fifty characters.");
        });
    }

    @Test
    public void testValidExactDate() {
        // Exact current date (allowed since it is not in the past)
        Appointment appointment = new Appointment("1234567890", new Date(), "Valid description");
        assertNotNull(appointment.getAppointmentDate());
    }
}
