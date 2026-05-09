package com.example.profile.service.serviceImp;

import com.example.profile.dto.DoctorDTO;
import com.example.profile.dto.DoctorDropDown;
import com.example.profile.entity.Doctor;
import com.example.profile.exception.HMSException;
import com.example.profile.repository.DoctorRepository;
import com.example.profile.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
public class DoctorServiceImplementation implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public Long addDoctor(DoctorDTO doctorDTO) {
        if (doctorDTO.getEmail() != null && doctorRepository.findByEmail(doctorDTO.getEmail()).isPresent())
            throw new HMSException("DOCTOR_ALREADY_EXISTS");

        if (doctorDTO.getLicenseNumber() != null && doctorRepository.findByLicenseNumber(doctorDTO.getLicenseNumber()).isPresent())
            throw new HMSException("DOCTOR_ALREADY_EXISTS");
        return doctorRepository.save(doctorDTO.toEntity()).getId();
    }

    @Override
    public DoctorDTO getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new HMSException("DOCTOR_NOT_FOUND")).toDTO();
    }

    @Override
    public DoctorDTO updateDoctor(DoctorDTO doctorDTO) {
        doctorRepository.findById(doctorDTO.getId()).orElseThrow(() -> new HMSException("DOCTOR_NOT_FOUND"));
        return doctorRepository.save(doctorDTO.toEntity()).toDTO();
    }

    @Override
    public boolean doctorExists(Long id) {
        return doctorRepository.existsById(id);
    }

    @Override
    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doc -> new DoctorDTO(
                        doc.getId(),
                        doc.getName(),
                        doc.getEmail(),
                        doc.getDob(),
                        doc.getProfilePictureId(),
                        doc.getPhoneNo(),
                        doc.getAddress(),
                        doc.getLicenseNumber(),
                        doc.getSpecialization(),
                        doc.getDepartment(),
                        doc.getTotalExperience()
                ))
                .toList();
    }

    @Override
    public List<DoctorDropDown> getDropDown() {
        return doctorRepository.findAllDoctorDropDown();
    }

    @Override
    public void deleteDoctorById(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new HMSException("DOCTOR_NOT_FOUND"));
        doctorRepository.delete(doctor);
    }

    @Override
    public List<DoctorDropDown> getDoctorById(List<Long> ids) {
        return doctorRepository.findAllDoctorDropdownsByIds(ids);
    }
}
