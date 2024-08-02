package com.education.schoolautomation.service.impl;

import com.education.schoolautomation.dto.AssistantManagerDto;
import com.education.schoolautomation.entity.AssistantManager;
import com.education.schoolautomation.exception.RecordNotFoundExceptions;
import com.education.schoolautomation.mapper.AssistantManagerMapper;
import com.education.schoolautomation.mapper.SchoolMapper;
import com.education.schoolautomation.repository.AssistantManagerRepository;
import com.education.schoolautomation.service.AssistantManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AssistantManagerServiceImpl implements AssistantManagerService {

    private final AssistantManagerRepository repository;
    private final SchoolServiceImpl schoolService;
    private final AssistantManagerMapper assistantManagerMapper;
    private final SchoolMapper schoolMapper;

    @Transactional
    @Override
    public AssistantManagerDto create(AssistantManagerDto dto) {
        return assistantManagerMapper.toDto(repository.save(assistantManagerMapper.toEntity(dto)));
    }

    @Override
    public void delete(UUID assistantManagerId) {
        repository.deleteById(assistantManagerId);
    }

    @Override
    public AssistantManagerDto update(UUID assistantManagerId, AssistantManagerDto dto) {
        AssistantManager exitAssistantManager = repository.findById(assistantManagerId)
                .orElseThrow(() -> new RecordNotFoundExceptions(4001, "AssistantManager not found"));

        AssistantManager assistantManager = assistantManagerMapper.toEntity(dto);
        assistantManager.setIdentityId(dto.getAssistantManagerId());
        if (dto.getSchool() != null) {
            exitAssistantManager.setSchool(schoolMapper.toEntity(dto.getSchool()));
        }

        assistantManager = repository.save(assistantManager);
        return assistantManagerMapper.toDto(assistantManager);
    }

    @Override
    public List<AssistantManagerDto> getAll() {
        return assistantManagerMapper.toDtoList(repository.findAll());
    }


    public AssistantManagerDto getById(UUID assistantManagerId) {
        return assistantManagerMapper.toDto(repository.findById(assistantManagerId).get());
    }


}
