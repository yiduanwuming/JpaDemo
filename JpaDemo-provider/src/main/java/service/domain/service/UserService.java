package service.domain.service;


import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import service.domain.entity.User;
import service.domain.face.UserRepository;
import service.infrastructure.util.DataUtils;
import service.interfaces.frm.FindByIdFrm;
import service.interfaces.frm.PageFindStudentConditionFrm;
import service.interfaces.frm.UpdateStudentFrm;
import service.interfaces.vo.UserVo;

import javax.persistence.criteria.Predicate;
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
        if (!userOptional.isPresent()) {
            return null;
        }
        User user = userOptional.get();
        UserVo userVo = new UserVo();
        DataUtils.copyProperties(user, userVo);
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
            val id = frm.getId();
            if (id != null) {
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