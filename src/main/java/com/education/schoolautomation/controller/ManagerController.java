package com.education.schoolautomation.controller;

import com.education.schoolautomation.dto.ManagerDto;
import com.education.schoolautomation.request.ManagerRequest;
import com.education.schoolautomation.response.ManagerResponse;
import com.education.schoolautomation.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/managers")
public class ManagerController {
    @Autowired
    private ManagerService service;

    @PostMapping
    public ManagerResponse create(@RequestBody ManagerRequest request){
        return toResponse(service.create(toDto(request)));
    }

    private ManagerResponse toResponse(ManagerDto dto) {
        ManagerResponse response= new ManagerResponse();
        response.setManagerId(dto.getManagerId());
        response.setFullName(dto.getFullName());
        response.setTckn(dto.getTckn());
        response.setAge(dto.getAge());
        response.setPhoneNumber(dto.getPhoneNumber());
        response.setAddress(dto.getAddress());
        response.setSsn(dto.getSsn());
        response.setSalary(dto.getSalary());
        return response;
    }

    private ManagerDto toDto(ManagerRequest request) {
        ManagerDto dto= new ManagerDto();
        dto.setFullName(request.getFullName());
        dto.setTckn(request.getTckn());
        dto.setAge(request.getAge());
        dto.setPhoneNumber(request.getPhoneNumber());
        dto.setAddress(request.getAddress());
        dto.setSsn(request.getSsn());
        dto.setSalary(request.getSalary());
        return dto;
    }
}
