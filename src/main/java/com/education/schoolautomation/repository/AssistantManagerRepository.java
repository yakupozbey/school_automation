package com.education.schoolautomation.repository;

import com.education.schoolautomation.entity.AssistantManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssistantManagerRepository extends JpaRepository<AssistantManager, UUID> {
}
