package mvc.promiseme.project;

import mvc.promiseme.project.dto.MemberDTO;
import mvc.promiseme.project.dto.ProjectRequestDTO;
import mvc.promiseme.project.dto.ProjectResponseDTO;
import mvc.promiseme.project.service.ProjectService;
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
        List<MemberDTO> memberDTOList = new ArrayList<>();
        memberDTOList.add(MemberDTO.builder().role("기획자").userId(1L).build());
        memberDTOList.add(MemberDTO.builder().role("디자이너").userId(2L).build());
        memberDTOList.add(MemberDTO.builder().role("프론트개발자").userId(1L).build());
        ProjectRequestDTO requestDTO = ProjectRequestDTO.builder()
                .category("웹개발").
                deadline(LocalDate.parse("2023-12-13"))
                .name("약속해줘")
                .start(LocalDate.parse("2023-12-13"))
                .memberList(memberDTOList).build();

        System.out.println(projectService.insert(requestDTO));
    }
}
