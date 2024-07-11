package com.education.schoolautomation.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolDto {
    private UUID schoolId;
    private String schoolType;
    private String schoolName;
    private String schoolAddress;

    private UUID managerId;
    private List<AssistantManagerDto> assistantManagers;
    private List<ClassRoomDto> classRooms;

    public SchoolDto(UUID schoolId) {
        this.schoolId = schoolId;
    }
}
