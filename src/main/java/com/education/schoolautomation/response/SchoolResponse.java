package com.education.schoolautomation.response;

import com.education.schoolautomation.dto.AssistantManagerDto;
import com.education.schoolautomation.dto.ClassRoomDto;
import com.education.schoolautomation.dto.ManagerDto;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolResponse {
    private UUID schoolId;
    private String schoolType;
    private String schoolName;
    private String schoolAddress;

    private ManagerDto manager;
    private List<AssistantManagerDto> assistantManagers;
    private List<ClassRoomDto> classRooms;
}
