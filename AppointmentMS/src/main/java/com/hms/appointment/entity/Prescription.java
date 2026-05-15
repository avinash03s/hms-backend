package com.hms.appointment.entity;

import com.hms.appointment.dto.PrescriptionDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;
    private Long doctorId;
    private String doctorName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
    private LocalDate prescriptionDate;
    private String prescriptionNotes;

    public Prescription(Long id) {
        this.id=id;
    }

    public PrescriptionDTO toDTO() {
        return new PrescriptionDTO(id, patientId, doctorId,doctorName,
                appointment.getId(), prescriptionDate, prescriptionNotes, null);
    }
}
