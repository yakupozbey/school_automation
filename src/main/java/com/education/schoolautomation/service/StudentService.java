package com.education.schoolautomation.service;

import com.education.schoolautomation.dto.StudentDto;
import com.education.schoolautomation.response.StudentResponse;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    StudentDto create(StudentDto dto);

    void delete(UUID studentId);

    List<StudentDto> getAll();

    StudentDto get(UUID studentId);

    StudentDto update(UUID studentId, StudentDto dto);
}
