package com.education.schoolautomation.mapper;

import com.education.schoolautomation.dto.BranchDto;
import com.education.schoolautomation.dto.ClassRoomDto;
import com.education.schoolautomation.dto.TeacherDto;
import com.education.schoolautomation.entity.Branch;
import com.education.schoolautomation.request.BranchRequest;
import com.education.schoolautomation.response.BranchResponse;
import com.education.schoolautomation.service.impl.ClassroomServiceImpl;
import com.education.schoolautomation.service.impl.TeacherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BranchMapper {

    private final ClassRoomMapper classRoomMapper;
    private final TeacherMapper teacherMapper;
    private final LessonMapper lessonMapper;

    private final ClassroomServiceImpl classroomService;
    private final TeacherServiceImpl teacherService;

    public BranchResponse toResponse(BranchDto dto) {
        BranchResponse response = new BranchResponse();
        response.setBranchId(dto.getBranchId());
        response.setBranchName(dto.getBranchName());
        response.setClassRoomId(dto.getClassRoom().getClassRoomId());
        response.setClassTeacherId(dto.getClassTeacher().getTeacherId());
        response.setLessons(dto.getLessons());
        return response;
    }

    public BranchDto toDto(BranchRequest request) {
        BranchDto dto = new BranchDto();
        dto.setBranchName(request.getBranchName());
        dto.setClassRoom(ClassRoomDto.builder().classRoomId(request.getClassRoomId()).build());
        dto.setClassTeacher(TeacherDto.builder().teacherId(request.getTeacherId()).build());
        return dto;
    }

    public List<BranchResponse> toResponseList(List<BranchDto> dtoList) {
        return dtoList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }


    public BranchDto toDto(Branch entity) {
        BranchDto dto = new BranchDto();
        dto.setBranchId(entity.getBranchId());
        dto.setBranchName(entity.getBranchName());
        dto.setClassRoom(classRoomMapper.toDto(entity.getClassRoom()));
        dto.setClassTeacher(teacherMapper.toDto(entity.getClassTeacher()));
        dto.setLessons(lessonMapper.toDtoList(entity.getLessons()));
        return dto;
    }

    public Branch toEntity(BranchDto dto) {
        Branch entity = new Branch();
        entity.setBranchName(dto.getBranchName());
        entity.setClassRoom(classroomService.findById(dto.getClassRoom().getClassRoomId()));
        entity.setClassTeacher(teacherService.findById(dto.getClassTeacher().getTeacherId()));
        return entity;
    }

    public List<BranchDto> toDtoList(List<Branch> entityList) {
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Branch> toEntityList(List<BranchDto> dtoList) {
        if (dtoList == null) {
            return Collections.emptyList();
        }
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
