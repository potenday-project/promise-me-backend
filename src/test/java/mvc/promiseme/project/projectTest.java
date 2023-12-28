package mvc.promiseme.project;

import mvc.promiseme.project.dto.MemberDTO;
import mvc.promiseme.project.dto.ProjectRequestDTO;
import mvc.promiseme.project.dto.ProjectResponseDTO;
import mvc.promiseme.project.dto.RecommendScheduleRequestDTO;
import mvc.promiseme.project.dto.MemberRequestDTO;
import mvc.promiseme.project.service.ProjectService;
import mvc.promiseme.project.service.RecommendService;
import mvc.promiseme.users.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class projectTest {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private RecommendService recommendService;
    @Autowired
    private UserService userService;

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

        }
    }
    @Test
    public void testInsert(){
        List<MemberRequestDTO> memberDTOList = new ArrayList<>();
        memberDTOList.add(MemberRequestDTO.builder().role("기획자").email("a@gmail.com").build());
        memberDTOList.add(MemberRequestDTO.builder().role("디자이너").email("b@gmail.com").build());
        memberDTOList.add(MemberRequestDTO.builder().role("프론트 개발자").email("c@gmail.com").build());
        memberDTOList.add(MemberRequestDTO.builder().role("백엔드 개발자").email("d@gmail.com").build());
        ProjectRequestDTO requestDTO = ProjectRequestDTO.builder()
                .category("웹개발").
                deadline(LocalDate.parse("2023-12-17"))
                .name("포텐데이")
                .start(LocalDate.parse("2023-12-03"))
                .memberList(memberDTOList).build();

        System.out.println(projectService.insert(requestDTO));
    }
    @Test
    public void testRecommendSchedule(){
        recommendService.recommendSchedule(new RecommendScheduleRequestDTO(8L,"웹개발","기획자,백엔드개발자,프론트개발자,디자이너",LocalDate.parse("2023-10-13"),LocalDate.parse("2023-12-14")));
    }

    @Test
    public void testCheckUSer(){
        Long id = userService.check("bbb@gmail.com");
        System.out.println("id" +id);
    }

}
