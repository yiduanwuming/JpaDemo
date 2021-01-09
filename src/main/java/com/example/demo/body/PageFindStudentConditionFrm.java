package com.example.demo.body;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PageFindStudentConditionFrm {
    @ApiModelProperty("主键")
    private String id;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty("性别")
    private Integer sexCode;
    @ApiModelProperty("页码")
    private int page = 1;
    @ApiModelProperty("页容量")
    private int size = 10;
}
