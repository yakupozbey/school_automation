package com.education.schoolautomation.dto;

import com.education.schoolautomation.entity.Student;
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

    private BranchDto branch;
    private TeacherDto teacher;
    private List<Student> students;
}
