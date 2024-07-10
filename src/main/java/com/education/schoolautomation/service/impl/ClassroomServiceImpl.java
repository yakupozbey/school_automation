package com.education.schoolautomation.service.impl;

import com.education.schoolautomation.dto.ClassRoomDto;
import com.education.schoolautomation.entity.ClassRoom;
import com.education.schoolautomation.repository.ClassRoomRepository;
import com.education.schoolautomation.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClassroomServiceImpl implements ClassRoomService {
    @Autowired
    private ClassRoomRepository repository;
    @Autowired
    private SchoolServiceImpl schoolService;
    @Autowired
    private BranchServiceImpl branchService;

    @Transactional
    @Override
    public ClassRoomDto create(ClassRoomDto dto) {
        return toDto(repository.save(toEntity(dto)));
    }

    public ClassRoomDto getById(UUID classRoomId) {
        return toDto(repository.findById(classRoomId).get());
    }


    // ClassRoom entity listesini StudentDto listesine dönüştürür
    public List<ClassRoomDto> toDtoList(List<ClassRoom> entityList) {
        return entityList.stream()
                .map(this::toDto) // Her bir ClassRoom entity'sini ClassRoomDto'ya dönüştür
                .collect(Collectors.toList()); // Dönüştürülen DTO'ları listeye topla ve döndür
    }

    // ClassRoomDto listesini Student entity listesine dönüştürür
    public List<ClassRoom> toEntityList(List<ClassRoomDto> dtoList) {
        return dtoList.stream()
                .map(this::toEntity) // Her bir ClassRoomDto'yu ClassRoom entity'sine dönüştür
                .collect(Collectors.toList()); // Dönüştürülen entity'leri listeye topla ve döndür
    }


    public ClassRoomDto toDto(ClassRoom entity) {
        ClassRoomDto dto = new ClassRoomDto();
        dto.setClassRoomId(entity.getClassRoomId());
        dto.setClassRoomName(entity.getClassRoomName());
        if (entity.getSchool() != null) {
            dto.setSchool(schoolService.toDto(entity.getSchool()));
        }
        if (entity.getBranches() != null) {
            dto.setBranches(branchService.toDtoList(entity.getBranches()));
        }
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


}
