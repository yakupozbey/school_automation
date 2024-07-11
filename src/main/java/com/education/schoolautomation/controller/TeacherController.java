package com.education.schoolautomation.controller;

import com.education.schoolautomation.dto.TeacherDto;
import com.education.schoolautomation.request.TeacherRequest;
import com.education.schoolautomation.response.TeacherResponse;
import com.education.schoolautomation.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService service;

    @PostMapping
    public TeacherResponse create(@RequestBody TeacherRequest request) {
        return toResponse(service.create(toDto(request)));
    }

    @DeleteMapping
    public void delete(@RequestParam(value = "teacherId") UUID teacherId) {
        service.delete(teacherId);
    }

    @PutMapping("/{teacherId}")
    public TeacherResponse update(@PathVariable(name = "teacherId") UUID teacherId, @RequestBody TeacherRequest request){
        return toResponse(service.update(teacherId, toDto(request)));
    }

    @GetMapping
    public List<TeacherResponse> getAll() {
        return toResponselist(service.getAll());
    }

    public List<TeacherResponse> toResponselist(List<TeacherDto> dtoList) {
        return dtoList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private TeacherResponse toResponse(TeacherDto dto) {
        TeacherResponse response = new TeacherResponse();
        response.setTeacherId(dto.getTeacherId());
        response.setFullName(dto.getFullName());
        response.setTckn(dto.getTckn());
        response.setAge(dto.getAge());
        response.setPhoneNumber(dto.getPhoneNumber());
        response.setAddress(dto.getAddress());
        response.setSsn(dto.getSsn());
        response.setSalary(dto.getSalary());
        return response;
    }

    private TeacherDto toDto(TeacherRequest request) {
        TeacherDto dto = new TeacherDto();
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
