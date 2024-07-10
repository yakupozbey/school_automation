package com.education.schoolautomation.dto;

import com.education.schoolautomation.entity.Identity;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagerDto extends Identity {
    private UUID managerId;
    private String ssn;
    private double salary;

    private String fullName;
    private String tckn;
    private String age;
    private String phoneNumber;
    private String address;



    public ManagerDto(UUID identityId) {
        this.managerId = managerId;
    }
}
