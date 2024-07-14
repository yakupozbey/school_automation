package com.education.schoolautomation.service.impl;


import com.education.schoolautomation.dto.LessonDto;
import com.education.schoolautomation.entity.Lesson;
import com.education.schoolautomation.repository.LessonRepository;
import com.education.schoolautomation.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class LessonServiceImpl implements LessonService {

    private final LessonRepository repository;

    private final TeacherServiceImpl teacherService;

    private final BranchServiceImpl branchService;

    private final StudentServiceImpl studentService;


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


    public Lesson findById(UUID lessonId) {
        return repository.findByLessonId(lessonId);
    }


    public List<LessonDto> toDtoList(List<Lesson> entityList) {
        if (entityList == null) {
            return Collections.emptyList();
        }
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Lesson> toEntityList(List<LessonDto> dtoList) {
        if (dtoList==null){
            return Collections.emptyList();
        }
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }


    public LessonDto toDto(Lesson entity) {
        LessonDto dto = new LessonDto();
        dto.setLessonId(entity.getLessonId());
        dto.setLessonName(entity.getLessonName());
        dto.setBranch(branchService.toDto(entity.getBranch()));
        dto.setLessonTeacher(teacherService.toDto(entity.getLessonTeacher()));
        dto.setStudents(studentService.toDtoList(entity.getStudents()));
        return dto;
    }

    public Lesson toEntity(LessonDto dto) {
        Lesson entity = new Lesson();
        entity.setLessonName(dto.getLessonName());
        entity.setLessonTeacher(teacherService.findById(dto.getLessonTeacher().getIdentityId()));
        return entity;
    }
}
