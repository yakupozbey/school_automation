package com.education.schoolautomation.controller;

import com.education.schoolautomation.dto.LessonDto;
import com.education.schoolautomation.request.LessonRequest;
import com.education.schoolautomation.response.LessonResponse;
import com.education.schoolautomation.service.LessonService;
import com.education.schoolautomation.service.impl.BranchServiceImpl;
import com.education.schoolautomation.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lessons")
public class LessonController {
    @Autowired
    private LessonService service;
    @Autowired
    private BranchServiceImpl branchService;
    @Autowired
    private TeacherServiceImpl teacherService;


    @PostMapping
    public LessonResponse create(@RequestBody LessonRequest request) {
        return toResponse(service.create(toDto(request)));
    }


    private LessonResponse toResponse(LessonDto dto) {
        LessonResponse response = new LessonResponse();
        response.setLessonId(dto.getLessonId());
        response.setLessonName(dto.getLessonName());
        response.setBranch(dto.getBranch());
        response.setTeacher(dto.getTeacher());
        response.setStudents(dto.getStudents());
        return response;
    }

    private LessonDto toDto(LessonRequest request) {
        LessonDto dto = new LessonDto();
        dto.setLessonName(request.getLessonName());
        dto.setBranch(branchService.getById(request.getBranchId()));
        dto.setTeacher(teacherService.getById(request.getTeacherId()));
        return dto;
    }
}
