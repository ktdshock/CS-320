// Kenneth Dandrow

package Appointment;

import java.util.HashMap;
import java.util.Map;

public class AppointmentService {
    private Map<String, Appointment> appointments;

    public AppointmentService() {
        this.appointments = new HashMap<>();
    }

    public void addAppointment(Appointment appointment) {
        if (appointment == null || appointment.getAppointmentID() == null) {
            throw new NullPointerException("Appointment or Appointment ID cannot be null.");
        }
        if (appointments.containsKey(appointment.getAppointmentID())) {
            throw new IllegalArgumentException("Appointment ID already exists.");
        }
        appointments.put(appointment.getAppointmentID(), appointment);
    }

    public void deleteAppointment(String appointmentID) {
        if (appointmentID == null) {
            throw new NullPointerException("Appointment ID cannot be null.");
        }
        if (!appointments.containsKey(appointmentID)) {
            throw new IllegalArgumentException("Appointment ID not found.");
        }
        appointments.remove(appointmentID);
    }

    public Appointment getAppointment(String appointmentID) {
        if (appointmentID == null) {
            return null;
        }
        return appointments.get(appointmentID);
    }
}
