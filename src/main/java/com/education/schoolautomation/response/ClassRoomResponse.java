package com.education.schoolautomation.response;

import com.education.schoolautomation.dto.BranchDto;
import com.education.schoolautomation.dto.SchoolDto;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassRoomResponse extends BaseResponse{
    private UUID classRoomId;
    private String classRoomName;

    private UUID schoolId;
    private List<BranchDto> branches;
}
