package com.education.schoolautomation.response;


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
public class BranchResponse extends BaseResponse{
    private UUID branchId;
    private String branchName;

    private UUID classRoomId;
    private UUID classTeacherId;
    private List<LessonDto> lessons;
}
