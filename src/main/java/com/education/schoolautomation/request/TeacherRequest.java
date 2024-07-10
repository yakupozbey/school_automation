package com.education.schoolautomation.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherRequest {
    private String ssn;
    private double salary;

    private String fullName;
    private String tckn;
    private String age;
    private String phoneNumber;
    private String address;
}
