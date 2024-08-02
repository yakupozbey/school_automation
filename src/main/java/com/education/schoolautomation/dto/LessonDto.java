package com.education.schoolautomation.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonDto {
    private UUID lessonId;
    private String lessonName;

    private UUID branchId;
    private TeacherDto lessonTeacher;
    private List<StudentDto> students;
}
