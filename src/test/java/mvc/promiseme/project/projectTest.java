package mvc.promiseme.project;

import mvc.promiseme.project.dto.ProjectRequestDTO;
import mvc.promiseme.project.dto.ProjectResponseDTO;
import mvc.promiseme.project.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
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
    @Test
    public void progress() {
        int progress= projectService.progress(1L);
        System.out.println("progress : "+ progress);
    }

    @Test
    public void testGetcategory() {
        List<String> result= projectService.categoryRanking();
        for(String s : result)
            System.out.println(s);
    }
    @Test
    public void testProjectAll(){
        List<ProjectResponseDTO> list = projectService.projectAll(1L);
        for(ProjectResponseDTO s : list){
            System.out.println(s.getProjectId());
            System.out.println(s.getName());
            System.out.println(s.getRole());

        }

    }
}
