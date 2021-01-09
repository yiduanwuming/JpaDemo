package com.example.demo.controller;

import com.example.demo.body.AddStudentFrm;
import com.example.demo.body.FindByIdFrm;
import com.example.demo.body.PageFindStudentConditionFrm;
import com.example.demo.body.UpdateStudentFrm;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.vo.UserVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public String insert(@RequestBody @Validated AddStudentFrm addStudentFrm) {
        User user = new User();
        user.setName(addStudentFrm.getName());
        user.setSex(addStudentFrm.getSexCode());
        userService.insert(user);
        return "新增成功";
    }

    @PostMapping("/findById")
    public UserVo findById(@RequestBody @Validated FindByIdFrm frm) {
       return userService.findById(frm);
    }

    @PostMapping("/updateById")
    public String updateById(@RequestBody @Validated UpdateStudentFrm frm) throws Exception {
        userService.updateById(frm);
        return "修改成功";
    }

    /**
     * 分页查询学生信息
     * @param frm
     * @return
     * @throws Exception
     */
    @PostMapping("/pageFindStudentCondition")
    public Page<User> pageFindStudentCondition(@RequestBody @Validated PageFindStudentConditionFrm frm){
        return userService.pageFindStudentCondition(frm);
    }
}
