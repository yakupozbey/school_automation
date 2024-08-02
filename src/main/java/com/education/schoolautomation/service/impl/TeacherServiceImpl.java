package com.education.schoolautomation.service.impl;


import com.education.schoolautomation.dto.TeacherDto;
import com.education.schoolautomation.entity.Teacher;
import com.education.schoolautomation.exception.RecordNotFoundExceptions;
import com.education.schoolautomation.mapper.TeacherMapper;
import com.education.schoolautomation.repository.TeacherRepository;
import com.education.schoolautomation.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository repository;
    private final TeacherMapper teacherMapper;

    @Override
    @Transactional
    public TeacherDto create(TeacherDto dto) {
        return teacherMapper.toDto(repository.save(teacherMapper.toEntity(dto)));
    }

    @Override
    public TeacherDto update(UUID teacherId, TeacherDto dto) {
        Teacher exitTeacher = repository.findById(teacherId)
                .orElseThrow(() -> new RecordNotFoundExceptions(4001, "User not found"));
        Teacher teacher = teacherMapper.toEntity(dto);
        teacher.setIdentityId(exitTeacher.getIdentityId());
        teacher = repository.save(teacher);
        return teacherMapper.toDto(teacher);
    }

    @Override
    @Transactional
    public void delete(UUID teacherId) {
        repository.deleteById(teacherId);
    }

    @Override
    public List<TeacherDto> getAll() {
        return teacherMapper.toDtoList(repository.findAll());
    }

    @Transactional
    public Teacher findById(UUID identityId) {
        return repository.findTeacherByIdentityId(identityId);
    }


}
