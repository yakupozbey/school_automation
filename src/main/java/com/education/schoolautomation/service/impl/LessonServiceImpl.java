package com.education.schoolautomation.service.impl;


import com.education.schoolautomation.dto.LessonDto;
import com.education.schoolautomation.entity.Lesson;
import com.education.schoolautomation.exception.RecordNotFoundExceptions;
import com.education.schoolautomation.mapper.StudentMapper;
import com.education.schoolautomation.repository.LessonRepository;
import com.education.schoolautomation.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final StudentMapper studentMapper;


    @Override
    @Transactional
    public LessonDto create(LessonDto dto) {
        return toDto(repository.save(toEntity(dto)));
    }

    @Override
    public LessonDto update(UUID lessonId, LessonDto dto) {
        Lesson exitLesson = repository.findById(lessonId)
                .orElseThrow(() -> new RecordNotFoundExceptions(4001, "Lesson not found"));
        exitLesson.setLessonName(dto.getLessonName());
        exitLesson.setLessonTeacher(teacherService.findById(dto.getLessonTeacher().getTeacherId()));
        exitLesson.setBranch(branchService.findById(dto.getBranchId()));
        if (dto.getStudents() != null) {
            exitLesson.setStudents(studentMapper.toEntityList(dto.getStudents()));
        }
        exitLesson= repository.save(exitLesson);
        return toDto(exitLesson);
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
        if (dtoList == null) {
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
        dto.setBranchId(entity.getBranch().getBranchId());
        dto.setLessonTeacher(teacherService.toDto(entity.getLessonTeacher()));
        if (entity.getStudents() != null) {
            dto.setStudents(studentMapper.toDtoList(entity.getStudents()));
        }

        return dto;
    }

    public Lesson toEntity(LessonDto dto) {
        Lesson entity = new Lesson();
        entity.setLessonName(dto.getLessonName());
        entity.setBranch(branchService.findById(dto.getBranchId()));
        entity.setLessonTeacher(teacherService.findById(dto.getLessonTeacher().getTeacherId()));
        return entity;
    }
}
