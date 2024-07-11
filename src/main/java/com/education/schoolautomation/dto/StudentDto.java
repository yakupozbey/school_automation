package com.education.schoolautomation.dto;

import com.education.schoolautomation.entity.Lesson;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private UUID studentId;
    private String studentNumber;

    private String fullName;
    private String tckn;
    private String age;
    private String phoneNumber;
    private String address;

    private UUID lessonId;
}
