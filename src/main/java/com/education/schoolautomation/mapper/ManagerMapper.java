package com.education.schoolautomation.mapper;

import com.education.schoolautomation.dto.ManagerDto;
import com.education.schoolautomation.entity.Manager;
import com.education.schoolautomation.request.ManagerRequest;
import com.education.schoolautomation.response.ManagerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ManagerMapper {

    public ManagerResponse toResponse(ManagerDto dto) {
        ManagerResponse response = new ManagerResponse();
        response.setManagerId(dto.getManagerId());
        response.setFullName(dto.getFullName());
        response.setTckn(dto.getTckn());
        response.setAge(dto.getAge());
        response.setPhoneNumber(dto.getPhoneNumber());
        response.setAddress(dto.getAddress());
        response.setSsn(dto.getSsn());
        response.setSalary(dto.getSalary());
        return response;
    }

    public ManagerDto toDto(ManagerRequest request) {
        ManagerDto dto = new ManagerDto();
        dto.setFullName(request.getFullName());
        dto.setTckn(request.getTckn());
        dto.setAge(request.getAge());
        dto.setPhoneNumber(request.getPhoneNumber());
        dto.setAddress(request.getAddress());
        dto.setSsn(request.getSsn());
        dto.setSalary(request.getSalary());
        return dto;
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
