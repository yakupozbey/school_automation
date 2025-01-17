package com.education.schoolautomation.request;


import lombok.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonRequest {

    private String lessonName;
    private UUID branchId;
    private UUID teacherId;
}
