package com.education.schoolautomation.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssistantManagerRequest {
    private String ssn;
    private double salary;
    private UUID schoolId;

    private String fullName;
    private String tckn;
    private String age;
    private String phoneNumber;
    private String address;
}
