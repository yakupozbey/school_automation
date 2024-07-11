package com.education.schoolautomation.repository;

import com.education.schoolautomation.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
    Teacher findByIdentityId(UUID identityId);
}
