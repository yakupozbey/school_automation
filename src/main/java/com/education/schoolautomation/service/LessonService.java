package com.education.schoolautomation.service;

import com.education.schoolautomation.dto.LessonDto;

import java.util.List;
import java.util.UUID;

public interface LessonService {
    LessonDto create(LessonDto dto);

    void delete(UUID lessonId);

    List<LessonDto> getAll();
}
