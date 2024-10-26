// Kenneth Dandrow

package Tests;

import Appointment.Appointment;
import Appointment.AppointmentService;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class AppointmentServiceTest {

    @Test
    public void testAddAppointment() {
        AppointmentService service = new AppointmentService();
        Appointment appointment = new Appointment("1234567890", new Date(), "Valid description");
        service.addAppointment(appointment);
        assertNotNull(service.getAppointment("1234567890"));
    }

    @Test
    public void testAddDuplicateAppointment() {
        AppointmentService service = new AppointmentService();
        Appointment appointment = new Appointment("1234567890", new Date(), "Valid description");
        service.addAppointment(appointment);
        
        // Try adding the same appointment again, expect an exception
        assertThrows(IllegalArgumentException.class, () -> {
            service.addAppointment(appointment);
        });
    }

    @Test
    public void testAddNullAppointment() {
        AppointmentService service = new AppointmentService();
        
        // Test with null appointment
        assertThrows(NullPointerException.class, () -> {
            service.addAppointment(null);
        });
    }

    @Test
    public void testDeleteAppointment() {
        AppointmentService service = new AppointmentService();
        Appointment appointment = new Appointment("1234567890", new Date(), "Valid description");
        service.addAppointment(appointment);
        
        // Delete the appointment and verify it no longer exists
        service.deleteAppointment("1234567890");
        assertNull(service.getAppointment("1234567890"));
    }

    @Test
    public void testDeleteNonExistentAppointment() {
        AppointmentService service = new AppointmentService();
        
        // Try deleting a non-existent appointment, expect an exception
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteAppointment("nonExistentID");
        });
    }

    @Test
    public void testDeleteNullAppointmentID() {
        AppointmentService service = new AppointmentService();
        
        // Test with null appointment ID
        assertThrows(NullPointerException.class, () -> {
            service.deleteAppointment(null);
        });
    }

    @Test
    public void testGetNonExistentAppointment() {
        AppointmentService service = new AppointmentService();
        
        // Attempt to retrieve a non-existent appointment, expect null
        assertNull(service.getAppointment("nonExistentID"));
    }

    @Test
    public void testGetNullAppointmentID() {
        AppointmentService service = new AppointmentService();
        
        // Test with null appointment ID, expect null
        assertNull(service.getAppointment(null));
    }

    @Test
    public void testGetAppointment() {
        AppointmentService service = new AppointmentService();
        Appointment appointment = new Appointment("1234567890", new Date(), "Valid description");
        service.addAppointment(appointment);
        
        // Retrieve the appointment and verify details
        Appointment retrievedAppointment = service.getAppointment("1234567890");
        assertNotNull(retrievedAppointment);
        assertEquals("1234567890", retrievedAppointment.getAppointmentID());
        assertEquals("Valid description", retrievedAppointment.getDescription());
    }
}
