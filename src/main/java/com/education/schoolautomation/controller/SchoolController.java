package com.education.schoolautomation.controller;


import com.education.schoolautomation.dto.ManagerDto;
import com.education.schoolautomation.dto.SchoolDto;
import com.education.schoolautomation.request.SchoolRequest;
import com.education.schoolautomation.response.SchoolResponse;
import com.education.schoolautomation.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schools")
public class SchoolController {
    @Autowired
    private SchoolService service;

    @PostMapping
    public SchoolResponse create(@RequestBody SchoolRequest request) {
        return toResponse(service.create(toDto(request)));
    }

    private SchoolResponse toResponse(SchoolDto dto) {
        SchoolResponse response = new SchoolResponse();
        response.setSchoolId(dto.getSchoolId());
        response.setSchoolType(dto.getSchoolType());
        response.setSchoolName(dto.getSchoolName());
        response.setSchoolAddress(dto.getSchoolAddress());

        if (dto.getManager() != null) {
            response.setManager(dto.getManager());
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
            dto.setManager(new ManagerDto(request.getManagerId())); //BU KATMANDA SERVİCE İNJECT ETTİĞİM KISIMLARI KALDIR.
            //BİR ÜSTTE YAZILAN KOD BLOĞUNDAKİ GİBİ YAP.
        }

        return dto;
    }
}
