package com.education.schoolautomation.controller;

import com.education.schoolautomation.mapper.TeacherMapper;
import com.education.schoolautomation.request.TeacherRequest;
import com.education.schoolautomation.response.TeacherResponse;
import com.education.schoolautomation.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService service;
    private final TeacherMapper teacherMapper;

    @PostMapping
    public TeacherResponse create(@RequestBody TeacherRequest request) {
        return teacherMapper.toResponse(service.create(teacherMapper.toDto(request)));
    }

    @PutMapping("/{teacherId}")
    public TeacherResponse update(@PathVariable(name = "teacherId") UUID teacherId, @RequestBody TeacherRequest request) {
        return teacherMapper.toResponse(service.update(teacherId, teacherMapper.toDto(request)));
    }

    @DeleteMapping
    public void delete(@RequestParam(value = "teacherId") UUID teacherId) {
        service.delete(teacherId);
    }

    @GetMapping
    public List<TeacherResponse> getAll() {
        return teacherMapper.toResponselist(service.getAll());
    }

}
