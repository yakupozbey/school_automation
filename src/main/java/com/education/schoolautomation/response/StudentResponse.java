package com.education.schoolautomation.response;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse extends BaseResponse{
    private UUID studentId;
    private String studentNumber;
    private String fullName;
    private String tckn;
    private String age;
    private String phoneNumber;
    private String address;

    private UUID lessonId;
}
