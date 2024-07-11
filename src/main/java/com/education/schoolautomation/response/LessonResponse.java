package com.education.schoolautomation.response;


import com.education.schoolautomation.entity.Student;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonResponse extends BaseResponse{
    private UUID lessonId;
    private String lessonName;

    private UUID branchId;
    private UUID lessonTeacherId;
    private List<Student> students;
}
