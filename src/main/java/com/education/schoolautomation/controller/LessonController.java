package com.education.schoolautomation.controller;

import com.education.schoolautomation.dto.BranchDto;
import com.education.schoolautomation.dto.LessonDto;
import com.education.schoolautomation.dto.TeacherDto;
import com.education.schoolautomation.request.LessonRequest;
import com.education.schoolautomation.response.LessonResponse;
import com.education.schoolautomation.service.LessonService;
import com.education.schoolautomation.service.impl.BranchServiceImpl;
import com.education.schoolautomation.service.impl.StudentServiceImpl;
import com.education.schoolautomation.service.impl.TeacherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class LessonController {

    private final LessonService service;

    private final BranchServiceImpl branchService;

    private final TeacherServiceImpl teacherService;

    private final StudentServiceImpl studentService;


    @PostMapping
    public LessonResponse create(@RequestBody LessonRequest request) {
        return toResponse(service.create(toDto(request)));
    }


    @DeleteMapping
    public void delete(@RequestParam(value = "lessonId") UUID lessonId) {
        service.delete(lessonId);
    }

    @GetMapping
    public List<LessonResponse> getAll() {
        return toResponseList(service.getAll());
    }

    private List<LessonResponse> toResponseList(List<LessonDto> dtoList) {
        return dtoList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }


    private LessonResponse toResponse(LessonDto dto) {
        LessonResponse response = new LessonResponse();
        response.setLessonId(dto.getLessonId());
        response.setLessonName(dto.getLessonName());
        if (dto != null) {
            response.setBranchId(dto.getBranch().getBranchId());
        }

        response.setLessonTeacherId(dto.getLessonTeacher().getTeacherId());

        if (dto.getStudents() != null) {
            response.setStudents(studentService.toEntityList(dto.getStudents()));
        }

        return response;
    }

    private LessonDto toDto(LessonRequest request) {
        LessonDto dto = new LessonDto();
        dto.setLessonName(request.getLessonName());
        dto.setBranch(BranchDto.builder().branchId(request.getBranchId()).build());
        dto.setLessonTeacher(TeacherDto.builder().teacherId(request.getTeacherId()).build());
        return dto;
    }
}
