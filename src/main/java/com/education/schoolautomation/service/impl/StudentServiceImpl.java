package com.education.schoolautomation.service.impl;


import com.education.schoolautomation.dto.StudentDto;
import com.education.schoolautomation.entity.Student;
import com.education.schoolautomation.repository.StudentRepository;
import com.education.schoolautomation.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository repository;
    @Autowired
    private LessonServiceImpl lessonService;

    @Override
    public StudentDto create(StudentDto dto) {
        return toDto(repository.save(toEntity(dto)));
    }

    @Override
    public void delete(UUID studentId) {
        repository.deleteById(studentId);
    }

    @Override
    public List<StudentDto> getAll() {
        return toDtoList(repository.findAll());
    }


    public List<StudentDto> toDtoList(List<Student> entityList) {
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Student> toEntityList(List<StudentDto> dtoList) {
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public StudentDto toDto(Student entity) {
        StudentDto dto = new StudentDto();
        dto.setStudentId(entity.getIdentityId());
        dto.setStudentNumber(entity.getStudentNumber());
        dto.setFullName(entity.getFullName());
        dto.setTckn(entity.getTckn());
        dto.setAge(entity.getAge());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAddress(entity.getAddress());
        dto.setLessonId(dto.getLessonId());
        return dto;
    }

    public Student toEntity(StudentDto dto) {
        Student entity = new Student();
        entity.setStudentNumber(dto.getStudentNumber());
        entity.setFullName(dto.getFullName());
        entity.setTckn(dto.getTckn());
        entity.setAge(dto.getAge());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAddress(dto.getAddress());
        entity.setLesson(lessonService.findById(dto.getLessonId()));
        return entity;
    }
}
