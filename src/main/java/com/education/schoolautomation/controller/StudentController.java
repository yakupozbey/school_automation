package com.education.schoolautomation.controller;


import com.education.schoolautomation.dto.LessonDto;
import com.education.schoolautomation.dto.StudentDto;
import com.education.schoolautomation.mapper.StudentMapper;
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
    private final StudentMapper studentMapper;

    @PostMapping
    public StudentResponse create(@RequestBody StudentRequest request) {
        return studentMapper.toResponse(service.create(studentMapper.toDto(request)));
    }

    @PutMapping
    public StudentResponse update(@RequestParam(value = "studentId") UUID studentId, @RequestBody StudentRequest request) {
        return studentMapper.toResponse(service.update(studentId, studentMapper.toDto(request)));
    }

    @DeleteMapping
    public void delete(@RequestParam(value = "studentId") UUID studentId) {
        service.delete(studentId);
    }

    @GetMapping("/{studentId}")
    public StudentResponse get(@PathVariable(value = "studentId") UUID studentId) {
        return studentMapper.toResponse(service.get(studentId));
    }

    @GetMapping
    public List<StudentResponse> getAll() {
        return studentMapper.toResponseList(service.getAll());
    }

}
