package com.example.profile.dto;

import com.example.profile.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    private Long id;

    private String name;
    private String email;
    private LocalDate dob;
    private Long profilePictureId;
    private String phoneNo;
    private String address;
    private String licenseNumber;
    private String specialization;
    private String department;
    private Integer totalExperience;
    private Boolean active;

    public Doctor toEntity() {
        return new Doctor(this.id, this.name, this.email, this.dob,this.profilePictureId, this.phoneNo, this.address
                , this.licenseNumber, this.specialization, this.department, this.totalExperience,true);
    }
    //Converts Entity (Doctor) into DTO (DoctorDTO)
    //Fetch data from DB (Entity)
    //Convert → DTO → send to frontend


}
