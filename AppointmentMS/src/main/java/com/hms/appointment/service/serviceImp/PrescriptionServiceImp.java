package com.hms.appointment.service.serviceImp;

import com.hms.appointment.dto.PrescriptionDTO;
import com.hms.appointment.entity.Prescription;
import com.hms.appointment.exception.HMSException;
import com.hms.appointment.repository.PrescriptionRepository;
import com.hms.appointment.service.MedicineService;
import com.hms.appointment.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class PrescriptionServiceImp implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    private final MedicineService medicineService;

    @Override
    public Long savePrescription(PrescriptionDTO request) {
        // Set current date as prescription date
        request.setPrescriptionDate(LocalDate.now());
        Long prescriptionId = prescriptionRepository.save(request.toEntity()).getId();

        // Set prescription id inside every medicine
        request.getMedicines().forEach(medicine -> {
            medicine.setPrescriptionId(prescriptionId);
        });
        medicineService.saveAllMedicine(request.getMedicines());
        // Set generated id back into DTO
        request.setId(prescriptionId);
        return prescriptionId;
    }

    @Override
    public PrescriptionDTO getPrescriptionByAppointmentId(Long appointmentId) {
        PrescriptionDTO prescriptionDTO = prescriptionRepository.findByAppointment_Id(appointmentId)
                .orElseThrow(() -> new HMSException("PRESCRIPTION_NOT_FOUND")).toDTO();
        prescriptionDTO.setMedicines(medicineService.getAllMedicinesByPrescriptionId(prescriptionDTO.getId()));
        return prescriptionDTO;
    }

    @Override
    public PrescriptionDTO getPrescriptionById(Long prescriptionId) {
        PrescriptionDTO prescriptionDTO = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new HMSException("PRESCRIPTION_NOT_FOUND")).toDTO();
        prescriptionDTO.setMedicines(medicineService.getAllMedicinesByPrescriptionId(prescriptionDTO.getId()));
        return prescriptionDTO;
    }

    @Override
    public void deleteByAppointmentId(Long appointmentId) {
        Prescription prescription = prescriptionRepository.findByAppointment_Id(appointmentId)
                .orElseThrow(() -> new HMSException("PRESCRIPTION_NOT_FOUND"));
        medicineService.deleteByPrescriptionId(prescription.getId());
        prescriptionRepository.delete(prescription);
    }
}
