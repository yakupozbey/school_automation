package com.education.schoolautomation.controller;

import com.education.schoolautomation.dto.BranchDto;
import com.education.schoolautomation.request.BranchRequest;
import com.education.schoolautomation.response.BranchResponse;
import com.education.schoolautomation.service.BranchService;
import com.education.schoolautomation.service.impl.ClassroomServiceImpl;
import com.education.schoolautomation.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/branches")
public class BranchController {
    @Autowired
    private BranchService service;
    @Autowired
    private ClassroomServiceImpl classroomService;
    @Autowired
    private TeacherServiceImpl teacherService;


    @PostMapping
    public BranchResponse create(@RequestBody BranchRequest request) {
        return toResponse(service.create(toDto(request)));
    }

    private BranchResponse toResponse(BranchDto dto) {
        BranchResponse response = new BranchResponse();
        response.setBranchId(dto.getBranchId());
        response.setBranchName(dto.getBranchName());
        response.setClassRoom(dto.getClassRoom());
        response.setClassTeacher(dto.getClassTeacher());
        response.setLessons(dto.getLessons());
        return response;
    }

    private BranchDto toDto(BranchRequest request) {
        BranchDto dto = new BranchDto();
        dto.setBranchName(request.getBranchName());
        dto.setClassRoom(classroomService.getById(request.getClassRoomId()));
        dto.setClassTeacher(teacherService.getById(request.getTeacherId()));
        return dto;
    }
}
