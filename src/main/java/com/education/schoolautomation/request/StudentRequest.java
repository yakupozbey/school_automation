package com.education.schoolautomation.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest {
    private String studentNumber;

    private String fullName;
    private String tckn;
    private String age;
    private String phoneNumber;
    private String address;

    private UUID lessonId;
}
