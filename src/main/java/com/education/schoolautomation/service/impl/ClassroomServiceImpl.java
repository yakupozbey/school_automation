package com.education.schoolautomation.service.impl;

import com.education.schoolautomation.dto.ClassRoomDto;
import com.education.schoolautomation.entity.ClassRoom;
import com.education.schoolautomation.repository.ClassRoomRepository;
import com.education.schoolautomation.service.ClassRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassRoomService {

    private final ClassRoomRepository repository;

    private final SchoolServiceImpl schoolService;

    private final BranchServiceImpl branchService;

    @Transactional
    @Override
    public ClassRoomDto create(ClassRoomDto dto) {
        return toDto(repository.save(toEntity(dto)));
    }

    @Override
    @Transactional
    public void delete(UUID classRoomId) {
        repository.deleteById(classRoomId);
    }

    @Override
    public List<ClassRoomDto> getAll() {
        return toDtoList(repository.findAll());
    }


    @Transactional
    public ClassRoomDto getById(UUID classRoomId) {
        return toDto(findById(classRoomId));
    }

    public ClassRoom findById(UUID schoolId) {
        return repository.findByClassRoomId(schoolId);
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
            dto.setSchoolId(entity.getSchool().getSchoolId());
        }
        if (entity.getBranches() != null) {
            dto.setBranches(branchService.toDtoList(entity.getBranches()));
        }
        return dto;
    }

    public ClassRoom toEntity(ClassRoomDto dto) {
        ClassRoom entity = new ClassRoom();
        entity.setClassRoomName(dto.getClassRoomName());
        if (dto.getSchoolId() != null) {
            entity.setSchool(schoolService.findById(dto.getSchoolId()));
        }

        return entity;
    }


}
