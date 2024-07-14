package com.education.schoolautomation.controller;

import com.education.schoolautomation.dto.BranchDto;
import com.education.schoolautomation.dto.ClassRoomDto;
import com.education.schoolautomation.dto.TeacherDto;
import com.education.schoolautomation.request.BranchRequest;
import com.education.schoolautomation.response.BranchResponse;
import com.education.schoolautomation.service.BranchService;
import com.education.schoolautomation.service.impl.ClassroomServiceImpl;
import com.education.schoolautomation.service.impl.TeacherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/branches")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class BranchController {

    private final BranchService service;

    private final ClassroomServiceImpl classroomService;

    private final TeacherServiceImpl teacherService;


    @PostMapping
    @Transactional
    public BranchResponse create(@RequestBody BranchRequest request) {
        return toResponse(service.create(toDto(request)));
    }

    @DeleteMapping
    public void delete(@RequestParam(value = "branchId") UUID branchId) {
        service.delete(branchId);
    }

    @PutMapping
    public BranchResponse update(@RequestParam(value = "branchId") UUID branchId, BranchRequest request){
        return toResponse(service.update(branchId, toDto(request)));
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
        response.setBranchId(dto.getClassRoom().getClassRoomId());
        response.setClassTeacherId(dto.getClassTeacher().getTeacherId());
        response.setLessons(dto.getLessons());
        return response;
    }

    private BranchDto toDto(BranchRequest request) {
        BranchDto dto = new BranchDto();
        dto.setBranchName(request.getBranchName());
        dto.setClassRoom(ClassRoomDto.builder().classRoomId(request.getClassRoomId()).build());
        dto.setClassTeacher(TeacherDto.builder().teacherId(request.getTeacherId()).build());
        return dto;
    }
}
