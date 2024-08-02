package com.education.schoolautomation.mapper;

import com.education.schoolautomation.dto.ManagerDto;
import com.education.schoolautomation.dto.SchoolDto;
import com.education.schoolautomation.entity.School;
import com.education.schoolautomation.request.SchoolRequest;
import com.education.schoolautomation.response.SchoolResponse;
import com.education.schoolautomation.service.impl.ManagerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchoolMapper {

    private final ManagerServiceImpl managerService;
    private final ManagerMapper managerMapper;

    public SchoolResponse toResponse(SchoolDto dto) {
        SchoolResponse response = new SchoolResponse();
        response.setSchoolId(dto.getSchoolId());
        response.setSchoolType(dto.getSchoolType());
        response.setSchoolName(dto.getSchoolName());
        response.setSchoolAddress(dto.getSchoolAddress());

        if (dto.getManager() != null) {
            response.setManagerId(dto.getManager().getManagerId());
        }

        response.setAssistantManagers(dto.getAssistantManagers());
        response.setClassRooms(dto.getClassRooms());
        return response;
    }

    public SchoolDto toDto(SchoolRequest request) {
        SchoolDto dto = new SchoolDto();
        dto.setSchoolType(request.getSchoolType());
        dto.setSchoolName(request.getSchoolName());
        dto.setSchoolAddress(request.getSchoolAddress());
        dto.setManager(ManagerDto.builder().managerId(request.getManagerId()).build());
        return dto;
    }


    public SchoolDto toDto(School entity) {
        SchoolDto dto = new SchoolDto();
        dto.setSchoolId(entity.getSchoolId());
        dto.setSchoolType(entity.getSchoolType());
        dto.setSchoolName(entity.getSchoolName());
        dto.setSchoolAddress(entity.getSchoolAddress());

        if (entity.getManager() != null) {
            dto.setManager(managerMapper.toDto(entity.getManager()));
        }

        //dto.getAssistantManagers(assistantManagerService.toDtoList(entity.getAssistantManagers()));

        /*if (entity.getClassRooms() != null) {
            dto.setClassRooms(classroomService.toDtoList(entity.getClassRooms()));
        }*/

        return dto;
    }

    public SchoolDto toDtoSecond(School entity) {
        SchoolDto dto = new SchoolDto();
        dto.setSchoolId(entity.getSchoolId());
        dto.setSchoolType(entity.getSchoolType());
        dto.setSchoolName(entity.getSchoolName());
        dto.setSchoolAddress(entity.getSchoolAddress());
        return dto;
    }


    public School toEntity(SchoolDto dto) {
        School entity = new School();
        entity.setSchoolType(dto.getSchoolType());
        entity.setSchoolName(dto.getSchoolName());
        entity.setSchoolAddress(dto.getSchoolAddress());
        if (dto.getManager().getManagerId() != null) {
            entity.setManager(managerService.findById(dto.getManager().getManagerId()));
        }
        return entity;
    }
}
