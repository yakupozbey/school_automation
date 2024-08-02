package com.education.schoolautomation.service.impl;

import com.education.schoolautomation.dto.BranchDto;
import com.education.schoolautomation.entity.Branch;
import com.education.schoolautomation.entity.ClassRoom;
import com.education.schoolautomation.exception.RecordNotFoundExceptions;
import com.education.schoolautomation.mapper.BranchMapper;
import com.education.schoolautomation.repository.BranchRepository;
import com.education.schoolautomation.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class BranchServiceImpl implements BranchService {

    private final BranchRepository repository;
    private final ClassroomServiceImpl classroomService;
    private final TeacherServiceImpl teacherService;
    private final BranchMapper branchMapper;


    @Override
    @Transactional
    public BranchDto create(BranchDto dto) {
        return branchMapper.toDto(repository.save(branchMapper.toEntity(dto)));
    }

    @Override
    @Transactional
    public BranchDto update(UUID branchId, BranchDto dto) {
        Branch exitBranch = repository.findById(branchId).
                orElseThrow(() -> new RecordNotFoundExceptions(4001, "Branch not found"));
        exitBranch.setBranchName(dto.getBranchName());
        exitBranch.setClassRoom(classroomService.findById(dto.getClassRoom().getClassRoomId()));
        exitBranch.setClassTeacher(teacherService.findById(dto.getClassTeacher().getTeacherId()));
        //if (dto.getLessons() != null || !dto.getLessons().isEmpty()) {
        //  exitBranch.setLessons(lessonService.toEntityList(dto.getLessons()));
        //}

        exitBranch = repository.save(exitBranch);
        return branchMapper.toDto(exitBranch);
    }

    @Override
    public void delete(UUID branchId) {
        repository.deleteById(branchId);
    }

    @Override
    public List<BranchDto> getAll() {
        return branchMapper.toDtoList(repository.findAll());
    }

    public Branch findById(UUID branchId) {
        return repository.findById(branchId).
                orElseThrow(() -> new RecordNotFoundExceptions(4001, "Branch not found"));
    }

}
