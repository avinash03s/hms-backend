//package com.hms.appointment.clients;
//
//import com.hms.appointment.dto.DoctorDTO;
//import com.hms.appointment.dto.DoctorName;
//import com.hms.appointment.dto.PatientDTO;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class ProfileFallback implements ProfileClients {
//
//    @Override
//    public Boolean doctorExists(Long id) {
//        return null;
//    }
//
//    @Override
//    public Boolean patientExists(Long id) {
//        return null;
//    }
//
//    @Override
//    public PatientDTO getPatientById(Long id) {
//        return null;
//    }
//
//    @Override
//    public DoctorDTO getDoctorById(Long id) {
//        return null;
//    }
//
//    @Override
//    public List<DoctorName> getDoctorsById(List<Long> ids) {
//        return List.of();
//    }
//
//    @Override
//    public List<PatientDTO> getPatientsByIds(List<Long> ids) {
//        return List.of();
//    }
//
////    @Override
////    public List<DoctorName> getDoctorsById(List<Long> ids) {
////        return List.of();
////    }
//}