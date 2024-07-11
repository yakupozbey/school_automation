package com.education.schoolautomation.repository;

import com.education.schoolautomation.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LessonRepository extends JpaRepository<Lesson, UUID> {
    Lesson findByLessonId(UUID lessonId);
}
