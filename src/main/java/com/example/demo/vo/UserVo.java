package com.example.demo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class UserVo {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private Integer sex;
}
