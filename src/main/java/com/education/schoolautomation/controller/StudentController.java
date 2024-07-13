package com.education.schoolautomation.controller;


import com.education.schoolautomation.dto.LessonDto;
import com.education.schoolautomation.dto.StudentDto;
import com.education.schoolautomation.request.StudentRequest;
import com.education.schoolautomation.response.StudentResponse;
import com.education.schoolautomation.service.StudentService;
import com.education.schoolautomation.service.impl.LessonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class StudentController {

    private final StudentService service;

    private final LessonServiceImpl lessonService;

    @PostMapping
    public StudentResponse create(@RequestBody StudentRequest request) {
        return toResponse(service.create(toDto(request)));
    }

    @DeleteMapping
    public void delete(@RequestParam(value = "studentId") UUID studentId) {
        service.delete(studentId);
    }

    @GetMapping
    public List<StudentResponse> getAll() {
        return toResponseList(service.getAll());
    }

    private List<StudentResponse> toResponseList(List<StudentDto> dtoList) {
        return dtoList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
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
        response.setLessonId(dto.getLesson().getLessonId());
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
        dto.setLesson(LessonDto.builder().lessonId(request.getLessonId()).build());
        return dto;
    }
}
