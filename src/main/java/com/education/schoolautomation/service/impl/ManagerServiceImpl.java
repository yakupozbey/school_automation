package com.education.schoolautomation.service.impl;

import com.education.schoolautomation.dto.ManagerDto;
import com.education.schoolautomation.entity.Manager;
import com.education.schoolautomation.exception.RecordNotFoundExceptions;
import com.education.schoolautomation.repository.ManagerRepository;
import com.education.schoolautomation.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository repository;

    @Override
    @Transactional
    public ManagerDto create(ManagerDto dto) {
        return toDto(repository.save(toEntity(dto)));
    }

    @Override
    public void delete(UUID managerId) {
        repository.deleteById(managerId);
    }

    @Override
    public ManagerDto update(UUID managerId, ManagerDto dto) {
        Manager exitManager= repository.findByIdentityId(managerId);
        exitManager.setFullName(dto.getFullName());
        exitManager.setTckn(dto.getTckn());
        exitManager.setAge(dto.getAge());
        exitManager.setPhoneNumber(dto.getPhoneNumber());
        exitManager.setAddress(dto.getAddress());
        exitManager.setSsn(dto.getSsn());
        exitManager.setSalary(dto.getSalary());
        exitManager= repository.save(exitManager);
        return toDto(exitManager);
    }


    @Override
    public ManagerDto get(UUID managerId) {
        return toDto(repository.findById(managerId)
                .orElseThrow(() -> new RecordNotFoundExceptions(4001, "Manager not found")));
    }



    public ManagerDto getById(UUID managerId) {
        return toDto(repository.findById(managerId).get());
    }

    public Manager findById(UUID managerId) {
        return repository.findByIdentityId(managerId);
    }

    public ManagerDto toDto(Manager entity) {
        ManagerDto dto = new ManagerDto();
        dto.setManagerId(entity.getIdentityId());
        dto.setFullName(entity.getFullName());
        dto.setTckn(entity.getTckn());
        dto.setAge(entity.getAge());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAddress(entity.getAddress());
        dto.setSsn(entity.getSsn());
        dto.setSalary(entity.getSalary());
        return dto;
    }

    public Manager toEntity(ManagerDto dto) {
        Manager entity = new Manager();
        entity.setFullName(dto.getFullName());
        entity.setTckn(dto.getTckn());
        entity.setAge(dto.getAge());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAddress(dto.getAddress());
        entity.setSsn(dto.getSsn());
        entity.setSalary(dto.getSalary());
        return entity;
    }
}
