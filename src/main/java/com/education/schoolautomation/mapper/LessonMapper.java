package com.education.schoolautomation.mapper;

import com.education.schoolautomation.dto.LessonDto;
import com.education.schoolautomation.dto.TeacherDto;
import com.education.schoolautomation.entity.Lesson;
import com.education.schoolautomation.request.LessonRequest;
import com.education.schoolautomation.response.LessonResponse;
import com.education.schoolautomation.service.impl.BranchServiceImpl;
import com.education.schoolautomation.service.impl.TeacherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LessonMapper {

    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;
    private final BranchServiceImpl branchService;
    private final TeacherServiceImpl teacherService;

    public LessonResponse toResponse(LessonDto dto) {
        LessonResponse response = new LessonResponse();
        response.setLessonId(dto.getLessonId());
        response.setLessonName(dto.getLessonName());
        if (dto != null) {
            response.setBranchId(dto.getBranchId());
        }

        response.setLessonTeacherId(dto.getLessonTeacher().getTeacherId());

        if (dto.getStudents() != null) {
            response.setStudents(studentMapper.toEntityList(dto.getStudents()));
        }

        return response;
    }

    public LessonDto toDto(LessonRequest request) {
        LessonDto dto = new LessonDto();
        dto.setLessonName(request.getLessonName());
        dto.setBranchId(request.getBranchId());
        dto.setLessonTeacher(TeacherDto.builder().teacherId(request.getTeacherId()).build());
        return dto;
    }

    public List<LessonResponse> toResponseList(List<LessonDto> dtoList) {
        return dtoList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }


    public LessonDto toDto(Lesson entity) {
        LessonDto dto = new LessonDto();
        dto.setLessonId(entity.getLessonId());
        dto.setLessonName(entity.getLessonName());
        dto.setBranchId(entity.getBranch().getBranchId());
        dto.setLessonTeacher(teacherMapper.toDto(entity.getLessonTeacher()));
        if (entity.getStudents() != null) {
            dto.setStudents(studentMapper.toDtoList(entity.getStudents()));
        }
        return dto;
    }

    public Lesson toEntity(LessonDto dto) {
        Lesson entity = new Lesson();
        entity.setLessonName(dto.getLessonName());
        entity.setBranch(branchService.findById(dto.getBranchId()));
        entity.setLessonTeacher(teacherService.findById(dto.getLessonTeacher().getTeacherId()));
        return entity;
    }

    public List<LessonDto> toDtoList(List<Lesson> entityList) {
        if (entityList == null) {
            return Collections.emptyList();
        }
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
