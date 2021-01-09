package com.example.demo.contants;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentErrorCode extends RuntimeException {

    private Integer code;

    private String message;

    public StudentErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
