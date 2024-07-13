package com.education.schoolautomation.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BranchDto {
    private UUID branchId;
    private String branchName;

    private ClassRoomDto classRoom;
    private TeacherDto classTeacher;
    private List<LessonDto> lessons;
}
