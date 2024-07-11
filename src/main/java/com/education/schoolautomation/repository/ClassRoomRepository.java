package com.education.schoolautomation.repository;

import com.education.schoolautomation.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, UUID> {
    ClassRoom findByClassRoomId(UUID classRoomId);
}
