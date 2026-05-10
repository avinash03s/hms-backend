package com.hms.appointment.repository;

import com.hms.appointment.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    /// Method to find a prescription by appointment ID
    Optional<Prescription> findByAppointment_Id(Long appointmentId);

    /// Method to find all prescriptions by patient ID
    List<Prescription> findAllByPatientId(Long patientId);

    List<Prescription> findAllByDoctorIdOrderByPrescriptionDateDesc(Long doctorId);

}
