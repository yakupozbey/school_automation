package com.education.schoolautomation.service;

import com.education.schoolautomation.dto.BranchDto;

import java.util.List;
import java.util.UUID;

public interface BranchService {
    BranchDto create(BranchDto dto);

    void delete(UUID branchId);

    List<BranchDto> getAll();

    BranchDto update(UUID branchId, BranchDto dto);
}
