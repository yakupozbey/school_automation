package com.education.schoolautomation.response;

import com.education.schoolautomation.dto.ClassRoomDto;
import com.education.schoolautomation.dto.LessonDto;
import com.education.schoolautomation.dto.TeacherDto;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BranchResponse {
    private UUID branchId;
    private String branchName;

    private ClassRoomDto classRoom;
    private TeacherDto classTeacher;
    private List<LessonDto> lessons;
}
