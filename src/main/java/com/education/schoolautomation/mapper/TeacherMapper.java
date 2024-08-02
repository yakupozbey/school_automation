package com.education.schoolautomation.mapper;

import com.education.schoolautomation.dto.TeacherDto;
import com.education.schoolautomation.entity.Teacher;
import com.education.schoolautomation.request.TeacherRequest;
import com.education.schoolautomation.response.TeacherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TeacherMapper {

    public TeacherResponse toResponse(TeacherDto dto) {
        TeacherResponse response = new TeacherResponse();
        response.setTeacherId(dto.getTeacherId());
        response.setFullName(dto.getFullName());
        response.setTckn(dto.getTckn());
        response.setAge(dto.getAge());
        response.setPhoneNumber(dto.getPhoneNumber());
        response.setAddress(dto.getAddress());
        response.setSsn(dto.getSsn());
        response.setSalary(dto.getSalary());
        return response;
    }

    public TeacherDto toDto(TeacherRequest request) {
        TeacherDto dto = new TeacherDto();
        dto.setFullName(request.getFullName());
        dto.setTckn(request.getTckn());
        dto.setAge(request.getAge());
        dto.setPhoneNumber(request.getPhoneNumber());
        dto.setAddress(request.getAddress());
        dto.setSsn(request.getSsn());
        dto.setSalary(request.getSalary());
        return dto;
    }

    public List<TeacherResponse> toResponselist(List<TeacherDto> dtoList) {
        return dtoList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }


    public TeacherDto toDto(Teacher entity) {
        TeacherDto dto = new TeacherDto();
        dto.setTeacherId(entity.getIdentityId());
        dto.setFullName(entity.getFullName());
        dto.setTckn(entity.getTckn());
        dto.setAge(entity.getAge());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAddress(entity.getAddress());
        dto.setSsn(entity.getSsn());
        dto.setSalary(entity.getSalary());
        return dto;
    }

    public Teacher toEntity(TeacherDto dto) {
        Teacher entity = new Teacher();
        entity.setFullName(dto.getFullName());
        entity.setTckn(dto.getTckn());
        entity.setAge(dto.getAge());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAddress(dto.getAddress());
        entity.setSsn(dto.getSsn());
        entity.setSalary(dto.getSalary());
        return entity;
    }

    public List<TeacherDto> toDtoList(List<Teacher> entityList) {
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
