package service.interfaces.facade;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.domain.entity.User;
import service.domain.service.UserService;
import service.interfaces.frm.AddStudentFrm;
import service.interfaces.frm.FindByIdFrm;
import service.interfaces.frm.PageFindStudentConditionFrm;
import service.interfaces.frm.UpdateStudentFrm;
import service.interfaces.vo.UserVo;

@RestController
@RequestMapping(value = "/user")
@Api(tags = "用户相关接口")
public class UserFacade {
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
     */
    @PostMapping("/pageFindStudentCondition")
    public Page<User> pageFindStudentCondition(@RequestBody @Validated PageFindStudentConditionFrm frm){
        return userService.pageFindStudentCondition(frm);
    }
}
