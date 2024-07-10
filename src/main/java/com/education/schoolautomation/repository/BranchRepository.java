package com.education.schoolautomation.repository;

import com.education.schoolautomation.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BranchRepository extends JpaRepository<Branch, UUID> {
}
