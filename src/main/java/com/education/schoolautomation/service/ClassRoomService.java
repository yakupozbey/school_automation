package com.education.schoolautomation.service;

import com.education.schoolautomation.dto.ClassRoomDto;
import com.education.schoolautomation.request.ClassRoomRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClassRoomService {
    ClassRoomDto create(ClassRoomDto dto);

    void delete(Optional<UUID> classRoomId);

    List<ClassRoomDto> getAll();

    ClassRoomDto update(UUID classRoomId, ClassRoomDto dto);
}
