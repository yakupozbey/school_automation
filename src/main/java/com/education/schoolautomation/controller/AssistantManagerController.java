package com.education.schoolautomation.controller;

import com.education.schoolautomation.dto.AssistantManagerDto;
import com.education.schoolautomation.request.AssistantManagerRequest;
import com.education.schoolautomation.response.AssistantManagerResponse;
import com.education.schoolautomation.service.AssistantManagerService;
import com.education.schoolautomation.service.impl.SchoolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/assistant_managers")
public class AssistantManagerController {
    @Autowired
    private AssistantManagerService service;
    @Autowired
    private SchoolServiceImpl schoolService;


    @PostMapping
    public AssistantManagerResponse create(@RequestBody AssistantManagerRequest request) {
        return toResponse(service.create(toDto(request)));
    }

    @DeleteMapping
    public void delete(@RequestParam(value = "assistantManagerId") UUID assistantManagerId) {
        service.delete(assistantManagerId);
    }

    @GetMapping
    public List<AssistantManagerResponse> getAll() {
        return toResponseList(service.getAll());
    }

    public List<AssistantManagerResponse> toResponseList(List<AssistantManagerDto> dtoList) {
        return dtoList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private AssistantManagerResponse toResponse(AssistantManagerDto dto) {
        AssistantManagerResponse response = new AssistantManagerResponse();
        response.setAssistantManagerId(dto.getAssistantManagerId());
        response.setFullName(dto.getFullName());
        response.setTckn(dto.getTckn());
        response.setAge(dto.getAge());
        response.setPhoneNumber(dto.getPhoneNumber());
        response.setAddress(dto.getAddress());
        response.setSsn(dto.getSsn());
        response.setSalary(dto.getSalary());
        response.setSchool(dto.getSchool());
        return response;
    }

    private AssistantManagerDto toDto(AssistantManagerRequest request) {
        AssistantManagerDto dto = new AssistantManagerDto();
        dto.setFullName(request.getFullName());
        dto.setTckn(request.getTckn());
        dto.setAge(request.getAge());
        dto.setPhoneNumber(request.getPhoneNumber());
        dto.setAddress(request.getAddress());
        dto.setSsn(request.getSsn());
        dto.setSalary(request.getSalary());
        //dto.setSchool(new SchoolDto(request.getSchoolId()));
        dto.setSchool(schoolService.getById(request.getSchoolId()));
        return dto;
    }
}
