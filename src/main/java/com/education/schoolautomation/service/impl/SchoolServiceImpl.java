package com.education.schoolautomation.service.impl;


import com.education.schoolautomation.dto.SchoolDto;
import com.education.schoolautomation.entity.School;
import com.education.schoolautomation.exception.RecordNotFoundExceptions;
import com.education.schoolautomation.repository.SchoolRepository;
import com.education.schoolautomation.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository repository;
    private final ManagerServiceImpl managerService;
    private final AssistantManagerServiceImpl assistantManagerService;
    private final ClassroomServiceImpl classroomService;


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
    public SchoolDto update(UUID schoolId, SchoolDto dto) {
        School exitSchool = repository.findBySchoolId(schoolId);
        exitSchool.setSchoolType(dto.getSchoolType());
        exitSchool.setSchoolName(dto.getSchoolName());
        exitSchool.setSchoolAddress(dto.getSchoolAddress());

        if (dto.getAssistantManagers() != null) {
            exitSchool.setAssistantManagers(assistantManagerService.toEntityList(dto.getAssistantManagers()));
        } else {
            exitSchool.setAssistantManagers(Collections.emptyList());
        }

        if (dto.getClassRooms() != null) {
            exitSchool.setClassRooms(classroomService.toEntityList(dto.getClassRooms()));
        } else {
            exitSchool.setClassRooms(Collections.emptyList());
        }

        exitSchool = repository.save(exitSchool);
        return toDtoSecond(exitSchool);
    }

    @Override
    public SchoolDto get(UUID schoolId) {
        return toDto(repository.findById(schoolId)
                .orElseThrow(() -> new RecordNotFoundExceptions(4001, "User not found")));
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
            dto.setManager(managerService.toDto(entity.getManager()));
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
