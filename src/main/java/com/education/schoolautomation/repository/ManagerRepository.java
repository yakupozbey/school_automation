package com.education.schoolautomation.repository;

import com.education.schoolautomation.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ManagerRepository extends JpaRepository<Manager, UUID> {
    Manager findManagerByIdentityId(UUID identityId);
}
