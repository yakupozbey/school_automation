package com.education.schoolautomation.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssistantManagerDto {
    private UUID assistantManagerId;
    private String ssn;
    private double salary;
    private SchoolDto school;

    private String fullName;
    private String tckn;
    private String age;
    private String phoneNumber;
    private String address;
}
