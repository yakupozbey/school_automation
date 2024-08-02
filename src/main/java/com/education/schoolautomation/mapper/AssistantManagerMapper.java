package com.education.schoolautomation.mapper;

import com.education.schoolautomation.dto.AssistantManagerDto;
import com.education.schoolautomation.dto.SchoolDto;
import com.education.schoolautomation.entity.AssistantManager;
import com.education.schoolautomation.request.AssistantManagerRequest;
import com.education.schoolautomation.response.AssistantManagerResponse;
import com.education.schoolautomation.service.impl.SchoolServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AssistantManagerMapper {

    private final SchoolServiceImpl schoolService;
    private final SchoolMapper schoolMapper;

    public AssistantManagerResponse toResponse(AssistantManagerDto dto) {
        AssistantManagerResponse response = new AssistantManagerResponse();
        response.setAssistantManagerId(dto.getAssistantManagerId());
        response.setFullName(dto.getFullName());
        response.setTckn(dto.getTckn());
        response.setAge(dto.getAge());
        response.setPhoneNumber(dto.getPhoneNumber());
        response.setAddress(dto.getAddress());
        response.setSsn(dto.getSsn());
        response.setSalary(dto.getSalary());
        response.setSchool(dto.getSchool());
        return response;
    }

    public AssistantManagerDto toDto(AssistantManagerRequest request) {
        AssistantManagerDto dto = new AssistantManagerDto();
        dto.setFullName(request.getFullName());
        dto.setTckn(request.getTckn());
        dto.setAge(request.getAge());
        dto.setPhoneNumber(request.getPhoneNumber());
        dto.setAddress(request.getAddress());
        dto.setSsn(request.getSsn());
        dto.setSalary(request.getSalary());
        dto.setSchool(SchoolDto.builder().schoolId(request.getSchoolId()).build());
        return dto;
    }

    public List<AssistantManagerResponse> toResponseList(List<AssistantManagerDto> dtoList) {
        return dtoList.stream()
                .map(this::toResponse)
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
        if (entity.getSchool() != null) {
            dto.setSchool(schoolMapper.toDto(entity.getSchool()));
        }

        dto.setSalary(entity.getSalary());
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

    public List<AssistantManagerDto> toDtoList(List<AssistantManager> entityList) {
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    public List<AssistantManager> toEntityList(List<AssistantManagerDto> dtoList) {
        //dtoList null ise bana boş bir liste oluşturur
        if (dtoList == null) {
            return Collections.emptyList();
        }
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
