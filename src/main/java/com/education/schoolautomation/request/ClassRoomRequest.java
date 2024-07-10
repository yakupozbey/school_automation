package com.education.schoolautomation.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassRoomRequest {
    private String classRoomName;
    private UUID schoolId;
}
