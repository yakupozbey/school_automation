package com.education.schoolautomation.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecordNotFoundExceptions extends RuntimeException{
    public int code;
    public String message;
}
