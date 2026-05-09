package com.hms.appointment.controller;

import com.hms.appointment.dto.AppointmentDTO;
import com.hms.appointment.dto.AppointmentDetailsDTO;
import com.hms.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@Validated
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/schedule")
    public ResponseEntity<Long> scheduleAppointment(@RequestBody AppointmentDTO dto) {
        return new ResponseEntity<>(appointmentService.scheduleAppointment(dto), HttpStatus.CREATED);
    }

    @PutMapping("/cancel/{appointmentId}")
    public ResponseEntity<String> cancelAppoint(@PathVariable("appointmentId") Long id) {
        appointmentService.cancelAppointment(id);
        return new ResponseEntity<>("Appointment Cancel", HttpStatus.OK);
    }

    @GetMapping("/get/{appointmentId}")
    public ResponseEntity<AppointmentDTO> getAppointment(@PathVariable("appointmentId") Long id) {
        return new ResponseEntity<>(appointmentService.getAppointmentDetails(id), HttpStatus.OK);
    }

    @GetMapping("/get/details/{appointmentId}")
    public ResponseEntity<AppointmentDetailsDTO> getAppointmentDetailsWithName(@PathVariable("appointmentId") Long appointmentId) {
        return new ResponseEntity<>(
                appointmentService.getAppointmentDetailWithName(appointmentId), HttpStatus.OK);
    }

    @GetMapping("/getAllByPatient/{patientId}")
    public ResponseEntity<List<AppointmentDetailsDTO>> getAllAppointmentByPatientId(@PathVariable("patientId") Long patientId) {
        return new ResponseEntity<>(appointmentService.getAllAppointmentByPatientId(patientId), HttpStatus.OK);
    }

    @GetMapping("/getAllByDoctor/{doctorId}")
    public ResponseEntity<List<AppointmentDetailsDTO>> getAllAppointmentByDoctorId(@PathVariable("doctorId") Long doctorId) {
        return new ResponseEntity<>(appointmentService.getAllAppointmentByDoctorId(doctorId), HttpStatus.OK);
    }
}
