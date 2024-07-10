package com.education.schoolautomation.repository;

import com.education.schoolautomation.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SchoolRepository extends JpaRepository<School, UUID> {
    School findBySchoolId(UUID schoolId);
}
