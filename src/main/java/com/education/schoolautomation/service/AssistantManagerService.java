package com.education.schoolautomation.service;

import com.education.schoolautomation.dto.AssistantManagerDto;

import java.util.List;
import java.util.UUID;

public interface AssistantManagerService {
    AssistantManagerDto create(AssistantManagerDto dto);

    void delete(UUID assistantManagerId);

    List<AssistantManagerDto> getAll();
}
