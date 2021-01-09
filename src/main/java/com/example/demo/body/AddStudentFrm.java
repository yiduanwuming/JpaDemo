package com.example.demo.body;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AddStudentFrm {
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty
    private Integer age;
    @ApiModelProperty
    private Integer sexCode;
}
