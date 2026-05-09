package com.hms.appointment.service;

import com.hms.appointment.dto.PrescriptionDTO;

public interface PrescriptionService {

    Long savePrescription(PrescriptionDTO request);

    PrescriptionDTO getPrescriptionByAppointmentId(Long appointmentId);

    PrescriptionDTO getPrescriptionById(Long prescriptionId);
    void deleteByAppointmentId(Long appointmentId);
}
