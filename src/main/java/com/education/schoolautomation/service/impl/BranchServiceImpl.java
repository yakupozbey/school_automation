package com.education.schoolautomation.service.impl;

import com.education.schoolautomation.dto.BranchDto;
import com.education.schoolautomation.entity.Branch;
import com.education.schoolautomation.repository.BranchRepository;
import com.education.schoolautomation.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchRepository repository;
    @Autowired
    private ClassroomServiceImpl classroomService;
    @Autowired
    private TeacherServiceImpl teacherService;
    @Autowired
    private LessonServiceImpl lessonService;

    @Override
    public BranchDto create(BranchDto dto) {
        return toDto(repository.save(toEntity(dto)));
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
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public BranchDto toDto(Branch entity) {
        BranchDto dto = new BranchDto();
        dto.setBranchId(entity.getBranchId());
        dto.setBranchName(entity.getBranchName());
        dto.setClassRoom(classroomService.toDto(entity.getClassRoom()));
        dto.setClassTeacher(teacherService.toDto(entity.getClassTeacher()));
        dto.setLessons(lessonService.toDtoList(entity.getLessons()));
        return dto;
    }

    public Branch toEntity(BranchDto dto) {
        Branch entity = new Branch();
        entity.setBranchName(dto.getBranchName());
        entity.setClassRoom(classroomService.toEntity(dto.getClassRoom()));
        entity.setClassTeacher(teacherService.toEntity(dto.getClassTeacher()));
        return entity;
    }
}
