package com.education.schoolautomation.service.impl;


import com.education.schoolautomation.dto.LessonDto;
import com.education.schoolautomation.entity.Lesson;
import com.education.schoolautomation.repository.LessonRepository;
import com.education.schoolautomation.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonRepository repository;
    @Autowired
    private TeacherServiceImpl teacherService;
    @Autowired
    private BranchServiceImpl branchService;
    @Autowired
    private StudentServiceImpl studentService;


    @Override
    public LessonDto create(LessonDto dto) {
        return toDto(repository.save(toEntity(dto)));
    }

    @Override
    public void delete(UUID lessonId) {
        repository.deleteById(lessonId);
    }

    @Override
    public List<LessonDto> getAll() {
        return toDtoList(repository.findAll());
    }


    public LessonDto getById(UUID lessonId) {
        return toDto(findById(lessonId));
    }

    public Lesson findById(UUID lessonId) {
        return repository.findByLessonId(lessonId);
    }


    public List<LessonDto> toDtoList(List<Lesson> entityList) {
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Lesson> toEntityList(List<LessonDto> dtoList) {
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }


    public LessonDto toDto(Lesson entity) {
        LessonDto dto = new LessonDto();
        dto.setLessonId(entity.getLessonId());
        dto.setLessonName(entity.getLessonName());
        dto.setBranchId(entity.getBranch().getBranchId());
        dto.setLessonTeacherId(entity.getLessonTeacher().getIdentityId());
        dto.setStudents(studentService.toDtoList(entity.getStudents()));
        return dto;
    }

    public Lesson toEntity(LessonDto dto) {
        Lesson entity = new Lesson();
        entity.setLessonName(dto.getLessonName());
        entity.setLessonTeacher(teacherService.findById(dto.getLessonTeacherId()));
        return entity;
    }
}
