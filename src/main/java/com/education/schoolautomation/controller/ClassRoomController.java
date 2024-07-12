package com.education.schoolautomation.controller;

import com.education.schoolautomation.dto.ClassRoomDto;
import com.education.schoolautomation.dto.SchoolDto;
import com.education.schoolautomation.request.ClassRoomRequest;
import com.education.schoolautomation.response.ClassRoomResponse;
import com.education.schoolautomation.service.ClassRoomService;
import com.education.schoolautomation.service.impl.SchoolServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/class-rooms")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ClassRoomController {

    private final ClassRoomService service;

    private final SchoolServiceImpl schoolService;

    @PostMapping
    public ClassRoomResponse create(@RequestBody ClassRoomRequest request) {
        return toResponse(service.create(toDto(request)));
    }

    @DeleteMapping
    public void delete(@RequestParam(value = "classRoomId") UUID classRoomId) {
        service.delete(classRoomId);
    }

    @PutMapping
    public ClassRoomResponse update(@RequestParam(value = "classRoomId") UUID classRoomId, @RequestBody ClassRoomRequest request){
        return toResponse(service.update(classRoomId, toDto(request)));
    }

    @GetMapping
    public List<ClassRoomResponse> getAll() {
        return toResponseList(service.getAll());
    }

    public List<ClassRoomResponse> toResponseList(List<ClassRoomDto> dtoList) {
        return dtoList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private ClassRoomResponse toResponse(ClassRoomDto dto) {
        ClassRoomResponse response = new ClassRoomResponse();
        response.setClassRoomId(dto.getClassRoomId());
        response.setClassRoomName(dto.getClassRoomName());
        if (dto.getSchool() != null) {
            response.setSchoolId(dto.getSchool().getSchoolId());
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
            dto.setSchool(SchoolDto.builder().schoolId(request.getSchoolId()).build());
        }
        return dto;
    }
}
