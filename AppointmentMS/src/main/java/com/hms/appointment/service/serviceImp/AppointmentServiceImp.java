package com.hms.appointment.service.serviceImp;

import com.hms.appointment.constant.Status;
import com.hms.appointment.clients.ProfileClients;
import com.hms.appointment.dto.AppointmentDTO;
import com.hms.appointment.dto.AppointmentDetailsDTO;
import com.hms.appointment.dto.DoctorDTO;
import com.hms.appointment.dto.PatientDTO;
import com.hms.appointment.entity.Appointment;
import com.hms.appointment.exception.HMSException;
import com.hms.appointment.repository.AppointmentRepository;
import com.hms.appointment.service.ApiService;
import com.hms.appointment.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImp implements AppointmentService {


    private final AppointmentRepository appointmentRepository;


    private final ApiService apiService;

    private final ProfileClients profileClients;

    @Override
    public Long scheduleAppointment(AppointmentDTO appointmentDTO) {
        Boolean doctorExists = profileClients.doctorExists(appointmentDTO.getDoctorId());
        if (doctorExists == null || !doctorExists) {
            throw new HMSException("DOCTOR_NOT_FOUND");
        }
        Boolean patientExists = profileClients.patientExists(appointmentDTO.getPatientId());
        if (patientExists == null || !patientExists) {
            throw new HMSException("PATIENT_NOT_FOUND");
        }
        appointmentDTO.setStatus(Status.SCHEDULED);
        return appointmentRepository.save(appointmentDTO.toEntity()).getId();
    }

    @Override
    public void cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() ->
                new HMSException("APPOINTMENT_NOT_FOUND"));
        if (appointment.getStatus().equals(Status.CANCELLED)) {
            throw new HMSException("APPOINTMENT_ALREADY_CANCELLED");
        }
        appointment.setStatus(Status.CANCELLED);
        appointmentRepository.save(appointment);
    }

//    @Override
//    public void completeAppointment(Long appointmentId) {
//
//    }
//
//    @Override
//    public void rescheduleAppointment(Long appointmentId, String newDateTime) {
//
//    }

    @Override
    public AppointmentDTO getAppointmentDetails(Long appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new HMSException("APPOINTEMENT_NOT_FOUND"))
                .toDto();
    }

    @Override
    public AppointmentDetailsDTO getAppointmentDetailWithName(Long appointmentId) {
        AppointmentDTO appointmentDTO = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new HMSException("APPOINTMENT_NOT_FOUND"))
                .toDto();
        // Fetch doctor details from Profile Service
        DoctorDTO doctorDTO = profileClients
                .getDoctorById(appointmentDTO.getDoctorId());
        PatientDTO patientDTO = profileClients
                .getPatientById(appointmentDTO.getPatientId());

        if (patientDTO == null) {
            throw new HMSException("PATIENT_NOT_FOUND");
        }
        if (doctorDTO == null) {
            throw new HMSException("DOCTOR_NOT_FOUND");
        }

        return new AppointmentDetailsDTO(
                appointmentDTO.getId(),
                appointmentDTO.getPatientId(),
                patientDTO.getName(),
                patientDTO.getEmail(),
                patientDTO.getPhoneNo(),
                appointmentDTO.getDoctorId(),
                doctorDTO.getName(),
                appointmentDTO.getAppointmentTime(),
                appointmentDTO.getStatus(),
                appointmentDTO.getReason(),
                appointmentDTO.getNotes()
        );
    }

    @Override
    public List<AppointmentDetailsDTO> getAllAppointmentByPatientId(Long patientId) {
        return appointmentRepository.findAllByPatientId(patientId).stream()
                .map(appointment -> {
                    DoctorDTO doctorDTO =
                            profileClients.getDoctorById(appointment.getDoctorId());
                    appointment.setDoctorName(doctorDTO.getName());
                    return appointment;
                }).toList();
    }

    @Override
    public List<AppointmentDetailsDTO> getAllAppointmentByDoctorId(Long doctorId) {
        return appointmentRepository.findAllByDoctorId(doctorId).stream()
                .map(appointment -> {
                    PatientDTO patientDTO =
                            profileClients.getPatientById(appointment.getPatientId());
                    appointment.setPatientName(patientDTO.getName());
                    appointment.setPatientEmail(patientDTO.getName());
                    appointment.setPatientPhone(patientDTO.getPhoneNo());
                    return appointment;
                }).toList();
    }
}
