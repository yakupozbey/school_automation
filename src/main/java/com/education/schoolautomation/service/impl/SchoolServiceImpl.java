package com.education.schoolautomation.service.impl;


import com.education.schoolautomation.dto.SchoolDto;
import com.education.schoolautomation.entity.School;
import com.education.schoolautomation.exception.RecordNotFoundExceptions;
import com.education.schoolautomation.mapper.AssistantManagerMapper;
import com.education.schoolautomation.mapper.ClassRoomMapper;
import com.education.schoolautomation.mapper.ManagerMapper;
import com.education.schoolautomation.mapper.SchoolMapper;
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
    private final ClassroomServiceImpl classroomService;

    private final SchoolMapper schoolMapper;
    private final ManagerMapper managerMapper;
    private final ClassRoomMapper classRoomMapper;
    private final AssistantManagerMapper assistantManagerMapper;


    @Transactional
    @Override
    public SchoolDto create(SchoolDto dto) {
        return schoolMapper.toDto(repository.save(schoolMapper.toEntity(dto)));
    }

    @Override
    public SchoolDto update(UUID schoolId, SchoolDto dto) {
        School exitSchool = repository.findSchoolBySchoolId(schoolId);
        School school = schoolMapper.toEntity(dto);
        school.setSchoolId(exitSchool.getSchoolId());
        if (dto.getAssistantManagers() != null) {
            exitSchool.setAssistantManagers(assistantManagerMapper.toEntityList(dto.getAssistantManagers()));
        } else {
            exitSchool.setAssistantManagers(Collections.emptyList());
        }

        if (dto.getClassRooms() != null) {
            exitSchool.setClassRooms(classRoomMapper.toEntityList(dto.getClassRooms()));
        } else {
            exitSchool.setClassRooms(Collections.emptyList());
        }

        school = repository.save(school);
        return schoolMapper.toDtoSecond(school);
    }

    @Override
    @Transactional
    public void delete(UUID schoolId) {
        repository.deleteById(schoolId);
    }

    @Override
    public SchoolDto get(UUID schoolId) {
        return schoolMapper.toDto(repository.findById(schoolId)
                .orElseThrow(() -> new RecordNotFoundExceptions(4001, "User not found")));
    }

    @Transactional
    public School findById(UUID schoolId) {
        return repository.findSchoolBySchoolId(schoolId);
    }

}
