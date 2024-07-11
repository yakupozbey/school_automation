package com.education.schoolautomation.response;

import com.education.schoolautomation.dto.SchoolDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssistantManagerResponse extends BaseResponse{
    private UUID assistantManagerId;
    private String ssn;
    private double salary;
    private SchoolDto school;

    private String fullName;
    private String tckn;
    private String age;
    private String phoneNumber;
    private String address;
}
