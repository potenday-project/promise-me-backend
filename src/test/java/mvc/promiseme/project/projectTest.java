package mvc.promiseme.project;

import mvc.promiseme.project.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class projectTest {

    @Autowired
    private ProjectService projectService;
    @Test
    public void testGetDday() {
        int dday= projectService.dday(1L);
        System.out.println("dday : "+ dday);
    }
}
