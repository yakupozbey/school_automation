package com.education.schoolautomation.controller;


import com.education.schoolautomation.dto.StudentDto;
import com.education.schoolautomation.request.StudentRequest;
import com.education.schoolautomation.response.StudentResponse;
import com.education.schoolautomation.service.StudentService;
import com.education.schoolautomation.service.impl.LessonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService service;
    @Autowired
    private LessonServiceImpl lessonService;

    @PostMapping
    public StudentResponse create(@RequestBody StudentRequest request) {
        return toResponse(service.create(toDto(request)));
    }

    private StudentResponse toResponse(StudentDto dto) {
        StudentResponse response = new StudentResponse();
        response.setStudentId(dto.getStudentId());
        response.setStudentNumber(dto.getStudentNumber());
        response.setFullName(dto.getFullName());
        response.setTckn(dto.getTckn());
        response.setAge(dto.getAge());
        response.setPhoneNumber(dto.getPhoneNumber());
        response.setAddress(dto.getAddress());
        response.setLesson(dto.getLesson());
        return response;
    }

    private StudentDto toDto(StudentRequest request) {
        StudentDto dto = new StudentDto();
        dto.setStudentNumber(request.getStudentNumber());
        dto.setFullName(request.getFullName());
        dto.setTckn(request.getTckn());
        dto.setAge(request.getAge());
        dto.setPhoneNumber(request.getPhoneNumber());
        dto.setAddress(request.getAddress());
        dto.setLesson(lessonService.getById(request.getLessonId()));
        return dto;
    }
}
