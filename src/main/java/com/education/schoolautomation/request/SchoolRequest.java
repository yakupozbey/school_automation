package com.education.schoolautomation.request;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolRequest {
    private String schoolType;
    private String schoolName;
    private String schoolAddress;

    private UUID managerId;

}
