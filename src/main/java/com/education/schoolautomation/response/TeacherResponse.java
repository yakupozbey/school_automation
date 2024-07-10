package com.education.schoolautomation.response;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherResponse {
    private UUID teacherId;
    private String ssn;
    private double salary;

    private String fullName;
    private String tckn;
    private String age;
    private String phoneNumber;
    private String address;
}
