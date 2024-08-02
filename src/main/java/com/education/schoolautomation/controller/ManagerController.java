package com.education.schoolautomation.controller;

import com.education.schoolautomation.mapper.ManagerMapper;
import com.education.schoolautomation.request.ManagerRequest;
import com.education.schoolautomation.response.ManagerResponse;
import com.education.schoolautomation.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService service;
    private final ManagerMapper managerMapper;

    @PostMapping
    public ManagerResponse create(@RequestBody ManagerRequest request) {
        return managerMapper.toResponse(service.create(managerMapper.toDto(request)));
    }

    @DeleteMapping
    public void delete(@RequestParam(value = "managerId") UUID managerId) {
        service.delete(managerId);
    }

    @PutMapping
    public ManagerResponse update(@RequestParam(value = "managerId") UUID managerId, @RequestBody ManagerRequest request) {
        return managerMapper.toResponse(service.update(managerId, managerMapper.toDto(request)));
    }

    @GetMapping
    public ManagerResponse get(@RequestParam(value = "managerId") UUID managerId) {
        return managerMapper.toResponse(service.get(managerId));
    }

}
