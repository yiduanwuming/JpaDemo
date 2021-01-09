package com.example.demo.service;

import com.example.demo.body.FindByIdFrm;
import com.example.demo.body.PageFindStudentConditionFrm;
import com.example.demo.body.UpdateStudentFrm;
import com.example.demo.contants.StudentErrorCode;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.DataUtils;
import com.example.demo.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void insert(User user) {
        userRepository.save(user);
    }

    public UserVo findById(FindByIdFrm frm) {
        Optional<User> userOptional = userRepository.findById(frm.getId());
        User user = userOptional.get();
        UserVo userVo = new UserVo();
        DataUtils.copy(user, userVo);
        return userVo;
    }

    public void updateById(UpdateStudentFrm frm) throws Exception {
        Optional<User> userOptional = userRepository.findById(frm.getId());
        if (!userOptional.isPresent()) {
            throw new Exception("该学生不存在");
        }
        User user = userOptional.get();
        String name = frm.getName();
        if (!StringUtils.isEmpty(name)) {
            user.setName(name);
        }
        Integer sexCode = frm.getSexCode();
        if (sexCode != null) {
            user.setSex(sexCode);
        }
        userRepository.save(user);
    }

    public Page<User> pageFindStudentCondition(PageFindStudentConditionFrm frm) {
        Specification<User> specification = (root, criteriaQuery, criteriaBuilder) -> {
            Predicate conjunction = criteriaBuilder.conjunction();
            String id = frm.getId();
            if (!StringUtils.isEmpty(id)) {
                conjunction = criteriaBuilder.and(conjunction, criteriaBuilder.equal(root.get("id"), id));
            }
            String name = frm.getName();
            if (!StringUtils.isEmpty(name)) {
                conjunction = criteriaBuilder.and(conjunction, criteriaBuilder.equal(root.get("name"), name));
            }
            Integer sexCode = frm.getSexCode();
            if (sexCode != null) {
                conjunction = criteriaBuilder.and(conjunction, criteriaBuilder.equal(root.get("sex"), sexCode));
            }
//                Integer age = frm.getAge();
//                if (sexCode != age) {
//                    criteriaBuilder.and(criteriaBuilder.equal(root.get("sex"), sexCode));
//                }
            conjunction.getExpressions();
            return criteriaQuery.where(conjunction).getRestriction();
        };
        int page = frm.getPage();
        if (page > 0) {
            page--;
        }
        int size = frm.getSize();
        if (size == 0) {
            size = 10;
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        return userRepository.findAll(specification, pageRequest);
    }
}