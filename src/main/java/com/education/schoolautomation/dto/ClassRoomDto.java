package com.education.schoolautomation.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassRoomDto {
    private UUID classRoomId;
    private String classRoomName;

    private SchoolDto school;
    private List<BranchDto> branches;

}
