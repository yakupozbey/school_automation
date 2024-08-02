package com.education.schoolautomation.service.impl;


import com.education.schoolautomation.dto.StudentDto;
import com.education.schoolautomation.entity.Student;
import com.education.schoolautomation.exception.RecordNotFoundExceptions;
import com.education.schoolautomation.mapper.StudentMapper;
import com.education.schoolautomation.repository.StudentRepository;
import com.education.schoolautomation.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final LessonServiceImpl lessonService;
    private final StudentMapper studentMapper;

    @Override
    @Transactional
    public StudentDto create(StudentDto dto) {
        return studentMapper.toDto(repository.save(studentMapper.toEntity(dto)));
    }

    @Override
    public StudentDto update(UUID studentId, StudentDto dto) {
        Student exitStudent = repository.findById(studentId)
                .orElseThrow(() -> new RecordNotFoundExceptions(4001, "Student not found"));

        Student student= studentMapper.toEntity(dto);
        student.setIdentityId(exitStudent.getIdentityId());
        student = repository.save(student);
        return studentMapper.toDto(student);
    }

    @Override
    public void delete(UUID studentId) {
        repository.deleteById(studentId);
    }

    @Override
    public StudentDto get(UUID studentId) {
        return studentMapper.toDto(repository.findStudentByIdentityId(studentId));
    }

    @Override
    public List<StudentDto> getAll() {
        return studentMapper.toDtoList(repository.findAll());
    }


}
