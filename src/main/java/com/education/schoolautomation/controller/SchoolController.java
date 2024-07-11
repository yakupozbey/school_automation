package com.education.schoolautomation.controller;


import com.education.schoolautomation.dto.ManagerDto;
import com.education.schoolautomation.dto.SchoolDto;
import com.education.schoolautomation.request.SchoolRequest;
import com.education.schoolautomation.response.SchoolResponse;
import com.education.schoolautomation.service.SchoolService;
import com.education.schoolautomation.service.impl.ManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/schools")
public class SchoolController {
    @Autowired
    private SchoolService service;
    @Autowired
    private ManagerServiceImpl managerService;

    @PostMapping
    public SchoolResponse create(@RequestBody SchoolRequest request) {
        return toResponse(service.create(toDto(request)));
    }

    @DeleteMapping
    public void delete(@RequestParam(value = "schoolId") UUID schoolId) {
        service.delete(schoolId);
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

        if (dto.getManagerId() != null) {
            response.setManagerId(dto.getManagerId());
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

        if (request.getManagerId() != null) {
            dto.setManagerId(request.getManagerId());
            //dto.setManager(new ManagerDto(request.getManagerId())); //BU KATMANDA SERVİCE İNJECT ETTİĞİM KISIMLARI KALDIR.
            //BİR ÜSTTE YAZILAN KOD BLOĞUNDAKİ GİBİ YAP.
        }

        return dto;
    }
}
