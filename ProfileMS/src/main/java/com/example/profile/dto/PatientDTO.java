package com.example.profile.dto;

import com.example.profile.constant.BloodGroup;
import com.example.profile.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    private Long profilePictureId;
    private String phoneNo;
    private String address;
    private String aadharId;
    private BloodGroup bloodGroup;
    private String allergies;
    private String chronicDisease;
    private Boolean active;

    public Patient toEntity(){
        return new Patient(this.id, this.name, this.email,this.dob,this.profilePictureId
        ,this.phoneNo,this.address,this.aadharId,this.bloodGroup,this.allergies,this.chronicDisease,true);
    }

}
