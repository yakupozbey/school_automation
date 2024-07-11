package com.education.schoolautomation.service;

import com.education.schoolautomation.dto.TeacherDto;

import java.util.List;
import java.util.UUID;

public interface TeacherService {
    TeacherDto create(TeacherDto dto);

    void delete(UUID teacherId);

    List<TeacherDto> getAll();
}
