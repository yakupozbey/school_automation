package com.education.schoolautomation.mapper;

import com.education.schoolautomation.dto.StudentDto;
import com.education.schoolautomation.entity.Student;
import com.education.schoolautomation.request.StudentRequest;
import com.education.schoolautomation.response.StudentResponse;
import com.education.schoolautomation.service.impl.LessonServiceImpl;
import com.education.schoolautomation.service.impl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Component
public class StudentMapper {
    private final LessonServiceImpl lessonService;


    public  StudentResponse toResponse(StudentDto dto) {
        StudentResponse response = new StudentResponse();
        response.setStudentId(dto.getStudentId());
        response.setStudentNumber(dto.getStudentNumber());
        response.setFullName(dto.getFullName());
        response.setTckn(dto.getTckn());
        response.setAge(dto.getAge());
        response.setPhoneNumber(dto.getPhoneNumber());
        response.setAddress(dto.getAddress());
        response.setLessonId(dto.getLessonId());
        return response;
    }

    public  StudentDto toDto(StudentRequest request) {
        StudentDto dto = new StudentDto();
        dto.setStudentNumber(request.getStudentNumber());
        dto.setFullName(request.getFullName());
        dto.setTckn(request.getTckn());
        dto.setAge(request.getAge());
        dto.setPhoneNumber(request.getPhoneNumber());
        dto.setAddress(request.getAddress());
        dto.setLessonId(request.getLessonId());
        return dto;
    }

    public  List<StudentResponse> toResponseList(List<StudentDto> dtoList) {
        return dtoList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }



    public  StudentDto toDto(Student entity) {
        StudentDto dto = new StudentDto();
        dto.setStudentId(entity.getIdentityId());
        dto.setStudentNumber(entity.getStudentNumber());
        dto.setFullName(entity.getFullName());
        dto.setTckn(entity.getTckn());
        dto.setAge(entity.getAge());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAddress(entity.getAddress());
        dto.setLessonId(entity.getLesson().getLessonId());
        return dto;
    }

    public  Student toEntity(StudentDto dto) {
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


}
