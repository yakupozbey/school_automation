package com.education.schoolautomation.service.impl;

import com.education.schoolautomation.dto.BranchDto;
import com.education.schoolautomation.entity.Branch;
import com.education.schoolautomation.repository.BranchRepository;
import com.education.schoolautomation.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class BranchServiceImpl implements BranchService {

    private final BranchRepository repository;

    private final ClassroomServiceImpl classroomService;

    private final TeacherServiceImpl teacherService;

    private final LessonServiceImpl lessonService;

    @Override
    @Transactional
    public BranchDto create(BranchDto dto) {
        return toDto(repository.save(toEntity(dto)));
    }

    @Override
    public void delete(UUID branchId) {
        repository.deleteById(branchId);
    }

    @Override
    public List<BranchDto> getAll() {
        return toDtoList(repository.findAll());
    }


    public BranchDto getById(UUID branchId) {
        return toDto(repository.findById(branchId).get());
    }

    public List<BranchDto> toDtoList(List<Branch> entityList) {
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Branch> toEntityList(List<BranchDto> dtoList) {
        if (dtoList == null) {
            return Collections.emptyList();
        }
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public BranchDto toDto(Branch entity) {
        BranchDto dto = new BranchDto();
        dto.setBranchId(entity.getBranchId());
        dto.setBranchName(entity.getBranchName());
        dto.setClassRoomId(entity.getClassRoom().getClassRoomId());
        dto.setClassTeacherId(entity.getClassTeacher().getIdentityId());
        dto.setLessons(lessonService.toDtoList(entity.getLessons()));
        return dto;
    }

    public Branch toEntity(BranchDto dto) {
        Branch entity = new Branch();
        entity.setBranchName(dto.getBranchName());
        entity.setClassRoom(classroomService.findById(dto.getClassRoomId()));
        entity.setClassTeacher(teacherService.findById(dto.getClassTeacherId()));
        return entity;
    }
}
