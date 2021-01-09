package com.example.demo.controller;

import com.example.demo.body.AddStudentFrm;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
@Api(tags = "用户相关接口")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/insert")
    public void insert(@RequestBody @Validated AddStudentFrm addStudentFrm) {
        User user = new User();
        user.setName(addStudentFrm.getName());
        user.setSex(addStudentFrm.getSexCode());
        userService.insert(user);
    }
}
