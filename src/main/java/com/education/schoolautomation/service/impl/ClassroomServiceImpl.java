package com.education.schoolautomation.service.impl;

import com.education.schoolautomation.dto.ClassRoomDto;
import com.education.schoolautomation.entity.ClassRoom;
import com.education.schoolautomation.entity.School;
import com.education.schoolautomation.mapper.BranchMapper;
import com.education.schoolautomation.mapper.ClassRoomMapper;
import com.education.schoolautomation.repository.ClassRoomRepository;
import com.education.schoolautomation.service.ClassRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ClassroomServiceImpl implements ClassRoomService {


    private final ClassRoomRepository repository;
    private final ClassRoomMapper classRoomMapper;
    private final BranchMapper branchMapper;

    @Override
    @Transactional
    public ClassRoomDto create(ClassRoomDto dto) {
        return classRoomMapper.toDto(repository.save(classRoomMapper.toEntity(dto)));
    }

    @Override
    public ClassRoomDto update(UUID classRoomId, ClassRoomDto dto) {
        ClassRoom exitClassRoom = repository.findClassRoomByClassRoomId(classRoomId);
        exitClassRoom.setClassRoomName(dto.getClassRoomName());
        exitClassRoom.setSchool(School.builder().schoolId(dto.getSchool().getSchoolId()).build());

        if (dto.getBranches() != null) {
            exitClassRoom.setBranches(branchMapper.toEntityList(dto.getBranches()));
        } else {
            exitClassRoom.setBranches(Collections.emptyList());
        }
        exitClassRoom = repository.save(exitClassRoom);
        return classRoomMapper.toDto(exitClassRoom);
    }

    @Override
    @Transactional
    public void delete(Optional<UUID> classRoomId) {
        ClassRoom classRoom = repository.findClassRoomByClassRoomId(classRoomId.get());
        if (classRoom == null) {
            throw new NoSuchElementException("ClassRoom not found");
        }
        repository.deleteById(classRoomId.get());
    }

    @Override
    public List<ClassRoomDto> getAll() {
        return classRoomMapper.toDtoList(repository.findAll());
    }

    public ClassRoom findById(UUID classRoomId) {
        return repository.findClassRoomByClassRoomId(classRoomId);
    }

}
