package com.hms.appointment.dto;

import com.hms.appointment.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDetailsDTO {
    private Long id;
    private Long patientId;
    private String patientName;
    private String patientEmail;
    private String patientPhone;
    private Long doctorId;
    private String doctorName;
    private LocalDateTime appointmentTime;
    private Status status;
    private String reason;
    private String notes;
}
