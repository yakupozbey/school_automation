package com.education.schoolautomation.controller;

import com.education.schoolautomation.mapper.ClassRoomMapper;
import com.education.schoolautomation.request.ClassRoomRequest;
import com.education.schoolautomation.response.ClassRoomResponse;
import com.education.schoolautomation.service.ClassRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/class-rooms")
@RequiredArgsConstructor
public class ClassRoomController {

    private final ClassRoomService service;
    private final ClassRoomMapper classRoomMapper;

    @PostMapping
    public ClassRoomResponse create(@RequestBody ClassRoomRequest request) {
        return classRoomMapper.toResponse(service.create(classRoomMapper.toDto(request)));
    }

    @PutMapping
    public ClassRoomResponse update(@RequestParam(value = "classRoomId") UUID classRoomId, @RequestBody ClassRoomRequest request) {
        return classRoomMapper.toResponse(service.update(classRoomId, classRoomMapper.toDto(request)));
    }

    @DeleteMapping
    public void delete(@RequestParam(value = "classRoomId") Optional<UUID> classRoomId) {
        service.delete(classRoomId);
    }

    @GetMapping
    public List<ClassRoomResponse> getAll() {
        return classRoomMapper.toResponseList(service.getAll());
    }

}
