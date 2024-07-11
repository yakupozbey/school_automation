package com.education.schoolautomation.service;

import com.education.schoolautomation.dto.SchoolDto;

import java.util.UUID;

public interface SchoolService {
    SchoolDto create(SchoolDto dto);

    void delete(UUID schoolId);

    SchoolDto get(UUID schoolId);
}
