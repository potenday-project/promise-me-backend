package mvc.promiseme.project.service;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.common.exception.ErrorCode;
import mvc.promiseme.common.exception.UserException;
import mvc.promiseme.project.dto.MemberDTO;
import mvc.promiseme.project.dto.ProjectRequestDTO;
import mvc.promiseme.project.dto.ProjectResponseDTO;
import mvc.promiseme.project.entity.Member;
import mvc.promiseme.project.entity.MemberStatus;
import mvc.promiseme.project.entity.Project;
import mvc.promiseme.project.entity.Role;
import mvc.promiseme.project.repository.MemberRepository;
import mvc.promiseme.project.repository.ProjectRepository;
import mvc.promiseme.project.repository.RoleRepository;
import mvc.promiseme.todo.dto.TodoRequestDTO;
import mvc.promiseme.todo.entity.Todo;
import mvc.promiseme.users.entity.Users;
import mvc.promiseme.users.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    @Override
    @Transactional
    public List<ProjectResponseDTO> projectAll(Long userId) {
        Users user = userRepository.findById(userId).orElseThrow(()->new UserException(ErrorCode.INVALID_User_Login));
        List<Member> memberList = memberRepository.findByUsers(user);
        List<ProjectResponseDTO> projectList = new ArrayList<>();
        for(Member m : memberList)
            projectList.add(new ProjectResponseDTO(m.getProject().getProjectId(),m.getProject().getName(),m.getProject().getStart(),m.getProject().getDeadline()));
        return projectList;


    }
    @Override
    public List<String> categoryRanking() {
        return projectRepository.getCategoryRanking();
    }
    @Transactional
    @Override
    public String insert(ProjectRequestDTO projectRequestDTO) {
        try{
            Project p = new Project();
            Project project = p.mapToEntity(projectRequestDTO);
            projectRepository.save(project);
            System.out.println("projectId : " + project.getProjectId());
            List<MemberDTO> memberList = projectRequestDTO.getMemberList();

            for(MemberDTO m : memberList){
                Role r = new Role();
                Role role = roleRepository.findByName(m.getRole());
                if(role==null) {
                    role = Role.builder().name(m.getRole()).build();
                    roleRepository.save(role);
                }
                System.out.println("role name "+role.getName());
                Users user = userRepository.findById(m.getUserId()).orElseThrow(() -> new NoSuchElementException("[ERROR] 해당하는 사용자가 존재하지 않습니다."));
                Member member = Member.builder().project(project).role(role).users(user).status(MemberStatus.PARTICIPATION).build();
                memberRepository.save(member);
            }
            return "success";

        }catch (Exception e){
            return "error";
        }
    }

    @Override
    public int progress(Long projectId) {
        return projectRepository.getProgress(projectId);

    }

    @Override
    public int dday(Long projectId) {
        return projectRepository.findDday(projectId);
    }
}
