package com.education.schoolautomation.response;

import com.education.schoolautomation.dto.LessonDto;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {
    private UUID studentId;
    private String studentNumber;
    private String fullName;
    private String tckn;
    private String age;
    private String phoneNumber;
    private String address;

    private LessonDto lesson;
}
