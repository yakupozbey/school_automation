package com.education.schoolautomation.controller;

import com.education.schoolautomation.dto.ClassRoomDto;
import com.education.schoolautomation.dto.SchoolDto;
import com.education.schoolautomation.request.ClassRoomRequest;
import com.education.schoolautomation.response.ClassRoomResponse;
import com.education.schoolautomation.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/class-rooms")
public class ClassRoomController {
    @Autowired
    private ClassRoomService service;

    @PostMapping
    public ClassRoomResponse create(@RequestBody ClassRoomRequest request) {
        return toResponse(service.create(toDto(request)));
    }

    private ClassRoomResponse toResponse(ClassRoomDto dto) {
        ClassRoomResponse response = new ClassRoomResponse();
        response.setClassRoomId(dto.getClassRoomId());
        response.setClassRoomName(dto.getClassRoomName());
        if (dto.getSchool() != null) {
            response.setSchool(dto.getSchool());
        }
        if (dto.getBranches() != null) {
            response.setBranches(dto.getBranches());
        }

        return response;
    }

    private ClassRoomDto toDto(ClassRoomRequest request) {
        ClassRoomDto dto = new ClassRoomDto();
        dto.setClassRoomName(request.getClassRoomName());
        if (request.getSchoolId() != null) {
            dto.setSchool(new SchoolDto(request.getSchoolId()));
        }
        return dto;
    }
}
