package com.education.schoolautomation.controller;

import com.education.schoolautomation.dto.BranchDto;
import com.education.schoolautomation.request.BranchRequest;
import com.education.schoolautomation.response.BranchResponse;
import com.education.schoolautomation.service.BranchService;
import com.education.schoolautomation.service.impl.ClassroomServiceImpl;
import com.education.schoolautomation.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @DeleteMapping
    public void delete(@RequestParam(value = "branchId") UUID branchId) {
        service.delete(branchId);
    }

    @GetMapping
    public List<BranchResponse> getAll() {
        return toResponseList(service.getAll());
    }

    private List<BranchResponse> toResponseList(List<BranchDto> dtoList) {
        return dtoList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private BranchResponse toResponse(BranchDto dto) {
        BranchResponse response = new BranchResponse();
        response.setBranchId(dto.getBranchId());
        response.setBranchName(dto.getBranchName());
        response.setBranchId(dto.getClassRoomId());
        response.setClassTeacherId(dto.getClassTeacherId());
        response.setLessons(dto.getLessons());
        return response;
    }

    private BranchDto toDto(BranchRequest request) {
        BranchDto dto = new BranchDto();
        dto.setBranchName(request.getBranchName());
        dto.setClassRoomId(request.getClassRoomId());
        dto.setClassTeacherId(request.getTeacherId());
        return dto;
    }
}
