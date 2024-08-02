package com.education.schoolautomation.service.impl;

import com.education.schoolautomation.dto.ManagerDto;
import com.education.schoolautomation.entity.Manager;
import com.education.schoolautomation.exception.RecordNotFoundExceptions;
import com.education.schoolautomation.mapper.ManagerMapper;
import com.education.schoolautomation.repository.ManagerRepository;
import com.education.schoolautomation.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository repository;
    private final ManagerMapper managerMapper;

    @Override
    @Transactional
    public ManagerDto create(ManagerDto dto) {
        return managerMapper.toDto(repository.save(managerMapper.toEntity(dto)));
    }

    @Override
    public void delete(UUID managerId) {
        repository.deleteById(managerId);
    }

    @Override
    public ManagerDto update(UUID managerId, ManagerDto dto) {
        Manager exitManager = repository.findManagerByIdentityId(managerId);
        Manager manager = managerMapper.toEntity(dto);
        manager.setIdentityId(exitManager.getIdentityId());
        manager = repository.save(manager);
        return managerMapper.toDto(manager);
    }

    @Override
    public ManagerDto get(UUID managerId) {
        return managerMapper.toDto(repository.findById(managerId)
                .orElseThrow(() -> new RecordNotFoundExceptions(4001, "Manager not found")));
    }

    public Manager findById(UUID managerId) {
        return repository.findManagerByIdentityId(managerId);
    }


}
