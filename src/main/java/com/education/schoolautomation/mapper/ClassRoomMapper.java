package com.education.schoolautomation.mapper;

import com.education.schoolautomation.dto.ClassRoomDto;
import com.education.schoolautomation.dto.SchoolDto;
import com.education.schoolautomation.entity.ClassRoom;
import com.education.schoolautomation.request.ClassRoomRequest;
import com.education.schoolautomation.response.ClassRoomResponse;
import com.education.schoolautomation.service.impl.SchoolServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClassRoomMapper {

    private final SchoolServiceImpl schoolService;
    private final SchoolMapper schoolMapper;


    public ClassRoomResponse toResponse(ClassRoomDto dto) {
        ClassRoomResponse response = new ClassRoomResponse();
        response.setClassRoomId(dto.getClassRoomId());
        response.setClassRoomName(dto.getClassRoomName());
        if (dto.getSchool() != null) {
            response.setSchoolId(dto.getSchool().getSchoolId());
        }
        if (dto.getBranches() != null) {
            response.setBranches(dto.getBranches());
        }

        return response;
    }

    public ClassRoomDto toDto(ClassRoomRequest request) {
        ClassRoomDto dto = new ClassRoomDto();
        dto.setClassRoomName(request.getClassRoomName());

        if (request.getSchoolId() != null) {
            dto.setSchool(SchoolDto.builder().schoolId(request.getSchoolId()).build());
        }
        return dto;
    }

    public List<ClassRoomResponse> toResponseList(List<ClassRoomDto> dtoList) {
        return dtoList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }


    public ClassRoomDto toDto(ClassRoom entity) {
        ClassRoomDto dto = new ClassRoomDto();
        dto.setClassRoomId(entity.getClassRoomId());
        dto.setClassRoomName(entity.getClassRoomName());
        if (entity.getSchool() != null) {
            dto.setSchool(schoolMapper.toDto(entity.getSchool()));
        }
        /*if (entity.getBranches() != null) {
            dto.setBranches(branchService.toDtoList(entity.getBranches()));
        }*/
        return dto;
    }

    public ClassRoom toEntity(ClassRoomDto dto) {
        ClassRoom entity = new ClassRoom();
        entity.setClassRoomName(dto.getClassRoomName());

        if (dto.getSchool() != null) {
            entity.setSchool(schoolService.findById(dto.getSchool().getSchoolId()));
        }

        return entity;
    }

    // ClassRoom entity listesini StudentDto listesine dönüştürür
    public List<ClassRoomDto> toDtoList(List<ClassRoom> entityList) {
        return entityList.stream()
                .map(this::toDto) // Her bir ClassRoom entity'sini ClassRoomDto'ya dönüştür
                .collect(Collectors.toList()); // Dönüştürülen DTO'ları listeye topla ve döndür
    }

    // ClassRoomDto listesini Student entity listesine dönüştürür
    public List<ClassRoom> toEntityList(List<ClassRoomDto> dtoList) {
        if (dtoList == null) {
            return Collections.emptyList();
        }
        return dtoList.stream()
                .map(this::toEntity) // Her bir ClassRoomDto'yu ClassRoom entity'sine dönüştür
                .collect(Collectors.toList()); // Dönüştürülen entity'leri listeye topla ve döndür
    }

}
