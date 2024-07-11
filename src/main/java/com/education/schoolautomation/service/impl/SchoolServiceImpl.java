package com.education.schoolautomation.service.impl;


import com.education.schoolautomation.dto.SchoolDto;
import com.education.schoolautomation.entity.School;
import com.education.schoolautomation.exception.RecordNotFoundExceptions;
import com.education.schoolautomation.repository.SchoolRepository;
import com.education.schoolautomation.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolRepository repository;
    @Autowired
    private ManagerServiceImpl managerService;
    @Autowired
    private AssistantManagerServiceImpl assistantManagerService;
    @Autowired
    private ClassroomServiceImpl classroomService;


    @Transactional
    @Override
    public SchoolDto create(SchoolDto dto) {
        return toDto(repository.save(toEntity(dto)));
    }

    @Override
    @Transactional
    public void delete(UUID schoolId) {
        repository.deleteById(schoolId);
    }

    @Override
    public SchoolDto get(UUID schoolId) {
        return toDto(repository.findById(schoolId)
                .orElseThrow(() -> new RecordNotFoundExceptions(4001, "User not found")));
    }


    @Transactional
    public SchoolDto getById(UUID schoolId) {
        return toDto(findById(schoolId));
    }

    @Transactional
    public School findById(UUID schoolId) {
        return repository.findBySchoolId(schoolId);
    }

    public SchoolDto toDto(School entity) {
        SchoolDto dto = new SchoolDto();
        dto.setSchoolId(entity.getSchoolId());
        dto.setSchoolType(entity.getSchoolType());
        dto.setSchoolName(entity.getSchoolName());
        dto.setSchoolAddress(entity.getSchoolAddress());

        if (entity.getManager() != null) {
            dto.setManagerId(entity.getManager().getIdentityId());
        }
        if (entity.getAssistantManagers() != null) {
            dto.setAssistantManagers(assistantManagerService.toDtoList(entity.getAssistantManagers()));
        }
        if (entity.getClassRooms() != null) {
            dto.setClassRooms(classroomService.toDtoList(entity.getClassRooms()));
        }


        return dto;
    }

    public School toEntity(SchoolDto dto) {
        School entity = new School();
        entity.setSchoolType(dto.getSchoolType());
        entity.setSchoolName(dto.getSchoolName());
        entity.setSchoolAddress(dto.getSchoolAddress());

        if (dto.getSchoolId() != null) {
            entity.setManager(managerService.findById(dto.getManagerId()));
        }

        /*
        if (dto.getAssistantManagers() != null) {
            entity.setAssistantManagers(assistantManagerService.toEntityList(dto.getAssistantManagers()));
        }
        if (dto.getClassRooms() != null) {
            entity.setClassRooms(classroomService.toEntityList(dto.getClassRooms()));
        }
         */


        return entity;
    }
}
