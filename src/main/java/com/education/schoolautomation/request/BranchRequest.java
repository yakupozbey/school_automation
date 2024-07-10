package com.education.schoolautomation.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BranchRequest {
    private String branchName;
    private UUID classRoomId;
    private UUID teacherId;
}
