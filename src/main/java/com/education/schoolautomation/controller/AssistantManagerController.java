package com.education.schoolautomation.controller;

import com.education.schoolautomation.mapper.AssistantManagerMapper;
import com.education.schoolautomation.request.AssistantManagerRequest;
import com.education.schoolautomation.response.AssistantManagerResponse;
import com.education.schoolautomation.service.AssistantManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/assistant_managers")
@RequiredArgsConstructor
public class AssistantManagerController {

    private final AssistantManagerService service;
    private final AssistantManagerMapper assistantManagerMapper;


    @PostMapping
    public AssistantManagerResponse create(@RequestBody AssistantManagerRequest request) {
        return assistantManagerMapper.toResponse(service.create(assistantManagerMapper.toDto(request)));
    }

    @DeleteMapping
    public void delete(@RequestParam(value = "assistantManagerId") UUID assistantManagerId) {
        service.delete(assistantManagerId);
    }

    @PutMapping
    public AssistantManagerResponse update(@RequestParam(value = "assistantManagerId") UUID assistantManagerId, @RequestBody AssistantManagerRequest request) {
        return assistantManagerMapper.toResponse(service.update(assistantManagerId, assistantManagerMapper.toDto(request)));
    }

    @GetMapping
    public List<AssistantManagerResponse> getAll() {
        return assistantManagerMapper.toResponseList(service.getAll());
    }

}
