package com.education.schoolautomation.controller;


import com.education.schoolautomation.dto.ManagerDto;
import com.education.schoolautomation.dto.SchoolDto;
import com.education.schoolautomation.request.SchoolRequest;
import com.education.schoolautomation.response.SchoolResponse;
import com.education.schoolautomation.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService service;


    @PostMapping
    public SchoolResponse create(@RequestBody SchoolRequest request) {
        return toResponse(service.create(toDto(request)));
    }

    @DeleteMapping
    public void delete(@RequestParam(value = "schoolId") UUID schoolId) {
        service.delete(schoolId);
    }

    @PutMapping
    public SchoolResponse update(@RequestParam(value = "schoolId") UUID schoolId, @RequestBody SchoolRequest request) {
        return toResponse(service.update(schoolId, toDto(request)));
    }

    @GetMapping
    public SchoolResponse get(@RequestParam(value = "schoolId") UUID schoolId) {
        return toResponse(service.get(schoolId));
    }

    private SchoolResponse toResponse(SchoolDto dto) {
        SchoolResponse response = new SchoolResponse();
        response.setSchoolId(dto.getSchoolId());
        response.setSchoolType(dto.getSchoolType());
        response.setSchoolName(dto.getSchoolName());
        response.setSchoolAddress(dto.getSchoolAddress());

        if (dto.getManager() != null) {
            response.setManagerId(dto.getManager().getManagerId());
        }

        response.setAssistantManagers(dto.getAssistantManagers());
        response.setClassRooms(dto.getClassRooms());
        return response;
    }

    private SchoolDto toDto(SchoolRequest request) {
        SchoolDto dto = new SchoolDto();
        dto.setSchoolType(request.getSchoolType());
        dto.setSchoolName(request.getSchoolName());
        dto.setSchoolAddress(request.getSchoolAddress());
        dto.setManager(ManagerDto.builder().managerId(request.getManagerId()).build());
        return dto;
    }
}
