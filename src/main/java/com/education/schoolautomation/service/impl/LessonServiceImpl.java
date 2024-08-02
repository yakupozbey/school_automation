package com.education.schoolautomation.service.impl;


import com.education.schoolautomation.dto.LessonDto;
import com.education.schoolautomation.entity.Lesson;
import com.education.schoolautomation.exception.RecordNotFoundExceptions;
import com.education.schoolautomation.mapper.LessonMapper;
import com.education.schoolautomation.mapper.StudentMapper;
import com.education.schoolautomation.repository.LessonRepository;
import com.education.schoolautomation.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class LessonServiceImpl implements LessonService {

    private final LessonRepository repository;
    private final TeacherServiceImpl teacherService;
    private final BranchServiceImpl branchService;
    private final LessonMapper lessonMapper;
    private final StudentMapper studentMapper;


    @Override
    @Transactional
    public LessonDto create(LessonDto dto) {
        return lessonMapper.toDto(repository.save(lessonMapper.toEntity(dto)));
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
        exitLesson = repository.save(exitLesson);
        return lessonMapper.toDto(exitLesson);
    }

    @Override
    public void delete(UUID lessonId) {
        repository.deleteById(lessonId);
    }

    @Override
    public List<LessonDto> getAll() {
        return lessonMapper.toDtoList(repository.findAll());
    }

    public Lesson findById(UUID lessonId) {
        return repository.findByLessonId(lessonId);
    }

}
