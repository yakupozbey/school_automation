package com.education.schoolautomation.service.impl;

import com.education.schoolautomation.dto.ManagerDto;
import com.education.schoolautomation.entity.Manager;
import com.education.schoolautomation.repository.ManagerRepository;
import com.education.schoolautomation.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerRepository repository;

    @Override
    public ManagerDto create(ManagerDto dto) {
        return toDto(repository.save(toEntity(dto)));
    }


    public ManagerDto getById(UUID managerId){
        return toDto(repository.findById(managerId).get());
    }

    public Manager findById(UUID managerId){
        return repository.findByIdentityId(managerId);
    }

    public ManagerDto toDto(Manager entity) {
        ManagerDto dto= new ManagerDto();
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
        Manager entity= new Manager();
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
