package com.hms.appointment.service;

import com.hms.appointment.dto.AppointmentDTO;
import com.hms.appointment.dto.AppointmentDetailsDTO;

import java.util.List;

public interface AppointmentService {
    Long scheduleAppointment(AppointmentDTO appointmentDTO);

    void cancelAppointment(Long appointmentId);

//    void completeAppointment(Long appointmentId);
//
//    void rescheduleAppointment(Long appointmentId, String newDateTime);

    AppointmentDTO getAppointmentDetails(Long appointmentId);

    AppointmentDetailsDTO getAppointmentDetailWithName(Long appointmentId);

    List<AppointmentDetailsDTO>getAllAppointmentByPatientId(Long patientId);

    List<AppointmentDetailsDTO>getAllAppointmentByDoctorId(Long doctorId);

    List<AppointmentDTO> getAllAppointments();

    List<AppointmentDTO> getAllAppointmentDetails();
}
