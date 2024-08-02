package com.education.schoolautomation.controller;

import com.education.schoolautomation.mapper.LessonMapper;
import com.education.schoolautomation.request.LessonRequest;
import com.education.schoolautomation.response.LessonResponse;
import com.education.schoolautomation.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class LessonController {

    private final LessonService service;
    private final LessonMapper lessonMapper;

    @PostMapping
    public LessonResponse create(@RequestBody LessonRequest request) {
        return lessonMapper.toResponse(service.create(lessonMapper.toDto(request)));
    }

    @PutMapping
    public LessonResponse update(@RequestParam(value = "lessonId") UUID lessonId, @RequestBody LessonRequest request) {
        return lessonMapper.toResponse(service.update(lessonId, lessonMapper.toDto(request)));
    }

    @DeleteMapping
    public void delete(@RequestParam(value = "lessonId") UUID lessonId) {
        service.delete(lessonId);
    }

    @GetMapping
    @Transactional
    public List<LessonResponse> getAll() {
        return lessonMapper.toResponseList(service.getAll());
    }

}
