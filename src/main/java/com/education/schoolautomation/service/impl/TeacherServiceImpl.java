package com.education.schoolautomation.service.impl;


import com.education.schoolautomation.dto.TeacherDto;
import com.education.schoolautomation.entity.Teacher;
import com.education.schoolautomation.exception.RecordNotFoundExceptions;
import com.education.schoolautomation.repository.TeacherRepository;
import com.education.schoolautomation.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository repository;

    @Override
    @Transactional
    public TeacherDto create(TeacherDto dto) {
        return toDto(repository.save(toEntity(dto)));
    }

    @Override
    @Transactional
    public void delete(UUID teacherId) {
        repository.deleteById(teacherId);
    }

    @Override
    public TeacherDto update(UUID teacherId, TeacherDto dto) {
        Teacher exitTeacher = repository.findById(teacherId).orElseThrow(() -> new RecordNotFoundExceptions(4001, "User not found"));
        exitTeacher.setFullName(dto.getFullName());
        exitTeacher.setTckn(dto.getTckn());
        exitTeacher.setAge(dto.getAge());
        exitTeacher.setPhoneNumber(dto.getPhoneNumber());
        exitTeacher.setAddress(dto.getAddress());
        exitTeacher.setSsn(dto.getSsn());
        exitTeacher.setSalary(dto.getSalary());
        exitTeacher = repository.save(exitTeacher);
        return toDto(exitTeacher);
    }

    @Override
    public List<TeacherDto> getAll() {
        return toDtoList(repository.findAll());
    }


    @Transactional
    public Teacher findById(UUID identityId) {
        return repository.findTeacherByIdentityId(identityId);
    }

    public List<TeacherDto> toDtoList(List<Teacher> entityList) {
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Teacher> toEntityList(List<TeacherDto> dtoList) {
        return dtoList.stream()
                .map(this::toEntity)
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
}
