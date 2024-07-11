package com.education.schoolautomation.service;

import com.education.schoolautomation.dto.ClassRoomDto;
import com.education.schoolautomation.request.ClassRoomRequest;

import java.util.List;
import java.util.UUID;

public interface ClassRoomService {
    ClassRoomDto create(ClassRoomDto dto);

    void delete(UUID schoolId);

    List<ClassRoomDto> getAll();

    ClassRoomDto update(UUID classRoomId, ClassRoomDto dto);
}
