package com.education.schoolautomation.service.impl;

import com.education.schoolautomation.dto.AssistantManagerDto;
import com.education.schoolautomation.entity.AssistantManager;
import com.education.schoolautomation.repository.AssistantManagerRepository;
import com.education.schoolautomation.service.AssistantManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AssistantManagerServiceImpl implements AssistantManagerService {
    @Autowired
    private AssistantManagerRepository repository;
    @Autowired
    private SchoolServiceImpl schoolService;

    @Transactional
    @Override
    public AssistantManagerDto create(AssistantManagerDto dto) {
        return toDto(repository.save(toEntity(dto)));
    }

    @Override
    public void delete(UUID assistantManagerId) {
        repository.deleteById(assistantManagerId);
    }

    @Override
    public List<AssistantManagerDto> getAll() {
        return toDtoList(repository.findAll());
    }

    public AssistantManagerDto getById(UUID assistantManagerId) {
        return toDto(repository.findById(assistantManagerId).get());
    }


    public List<AssistantManagerDto> toDtoList(List<AssistantManager> entityList) {
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    public List<AssistantManager> toEntityList(List<AssistantManagerDto> dtoList) {
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }


    public AssistantManagerDto toDto(AssistantManager entity) {
        AssistantManagerDto dto = new AssistantManagerDto();
        dto.setAssistantManagerId(entity.getIdentityId());
        dto.setFullName(entity.getFullName());
        dto.setTckn(entity.getTckn());
        dto.setAge(entity.getAge());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAddress(entity.getAddress());
        dto.setSsn(entity.getSsn());
        dto.setSalary(entity.getSalary());
        dto.setSchool(schoolService.toDto(entity.getSchool()));
        return dto;
    }

    public AssistantManager toEntity(AssistantManagerDto dto) {
        AssistantManager entity = new AssistantManager();
        entity.setFullName(dto.getFullName());
        entity.setTckn(dto.getTckn());
        entity.setAge(dto.getAge());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAddress(dto.getAddress());
        entity.setSsn(dto.getSsn());
        entity.setSalary(dto.getSalary());
        entity.setSchool(schoolService.findById(dto.getSchool().getSchoolId()));
        return entity;
    }
}
