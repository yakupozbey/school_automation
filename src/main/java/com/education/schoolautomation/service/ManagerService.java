package com.education.schoolautomation.service;

import com.education.schoolautomation.dto.ManagerDto;

import java.util.List;
import java.util.UUID;

public interface ManagerService {
    ManagerDto create(ManagerDto dto);

    void delete(UUID managerId);

    ManagerDto get(UUID managerId);
}
