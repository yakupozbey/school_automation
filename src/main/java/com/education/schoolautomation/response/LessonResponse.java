package com.education.schoolautomation.response;

import com.education.schoolautomation.dto.BranchDto;
import com.education.schoolautomation.dto.TeacherDto;
import com.education.schoolautomation.entity.Student;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonResponse {
    private UUID lessonId;
    private String lessonName;

    private BranchDto branch;
    private TeacherDto teacher;
    private List<Student> students;
}
