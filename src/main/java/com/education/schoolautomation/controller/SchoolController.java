package com.education.schoolautomation.controller;

import com.education.schoolautomation.mapper.SchoolMapper;
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
    private final SchoolMapper schoolMapper;


    @PostMapping
    public SchoolResponse create(@RequestBody SchoolRequest request) {
        return schoolMapper.toResponse(service.create(schoolMapper.toDto(request)));
    }

    @PutMapping
    public SchoolResponse update(@RequestParam(value = "schoolId") UUID schoolId, @RequestBody SchoolRequest request) {
        return schoolMapper.toResponse(service.update(schoolId, schoolMapper.toDto(request)));
    }

    @DeleteMapping
    public void delete(@RequestParam(value = "schoolId") UUID schoolId) {
        service.delete(schoolId);
    }

    @GetMapping
    public SchoolResponse get(@RequestParam(value = "schoolId") UUID schoolId) {
        return schoolMapper.toResponse(service.get(schoolId));
    }

}
