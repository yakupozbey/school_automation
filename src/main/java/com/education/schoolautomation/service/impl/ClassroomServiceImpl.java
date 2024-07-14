package com.education.schoolautomation.service.impl;

import com.education.schoolautomation.dto.ClassRoomDto;
import com.education.schoolautomation.entity.ClassRoom;
import com.education.schoolautomation.entity.School;
import com.education.schoolautomation.repository.ClassRoomRepository;
import com.education.schoolautomation.service.ClassRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassRoomService {


    private final ClassRoomRepository repository;
    private final SchoolServiceImpl schoolService;
    private final BranchServiceImpl branchService;
    /* BranchServiceImpl context üzerinden method içinde çağırıldı. STACKOVERFLOWERROR hatası için yapıldı
    ApplicationContext context = new AnnotationConfigApplicationContext(BranchServiceImpl.class);
    BranchServiceImpl branchService = context.getBean(BranchServiceImpl.class);
    */

    @Override
    @Transactional
    public ClassRoomDto create(ClassRoomDto dto) {
        return toDto(repository.save(toEntity(dto)));
    }

    @Override
    @Transactional
    public void delete(Optional<UUID> classRoomId) {
        ClassRoom classRoom = repository.findByClassRoomId(classRoomId.get());
        if (classRoom == null) {
            throw new NoSuchElementException("ClassRoom not found");
        }
        repository.deleteById(classRoomId.get());
    }

    @Override
    public List<ClassRoomDto> getAll() {
        return toDtoList(repository.findAll());
    }

    @Override
    public ClassRoomDto update(UUID classRoomId, ClassRoomDto dto) {
        ClassRoom exitClassRoom = repository.findByClassRoomId(classRoomId);
        exitClassRoom.setClassRoomName(dto.getClassRoomName());
        exitClassRoom.setSchool(School.builder().schoolId(dto.getSchool().getSchoolId()).build());

        if (dto.getBranches() != null) {
            exitClassRoom.setBranches(branchService.toEntityList(dto.getBranches()));
        } else {
            exitClassRoom.setBranches(Collections.emptyList());
        }
        exitClassRoom = repository.save(exitClassRoom);
        return toDto(exitClassRoom);
    }


    public ClassRoom findById(UUID classRoomId) {
        return repository.findByClassRoomId(classRoomId);
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


    public ClassRoomDto toDto(ClassRoom entity) {
        ClassRoomDto dto = new ClassRoomDto();
        dto.setClassRoomId(entity.getClassRoomId());
        dto.setClassRoomName(entity.getClassRoomName());
        if (entity.getSchool() != null) {
            dto.setSchool(schoolService.toDto(entity.getSchool()));
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


}
