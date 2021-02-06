import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import service.DemoApplication;
import service.domain.service.UserService;
import service.interfaces.frm.FindByIdFrm;
import service.interfaces.vo.UserVo;

@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringRunner.class)
public class ServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void getUser() {
        final FindByIdFrm build = FindByIdFrm.builder()
                .id(1).build();
        final UserVo byId = userService.findById(build);
        System.out.println(byId.getName());
    }

}
