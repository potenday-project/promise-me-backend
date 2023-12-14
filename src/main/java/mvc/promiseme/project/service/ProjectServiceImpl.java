package mvc.promiseme.project.service;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.project.dto.ProjectRequestDTO;
import mvc.promiseme.project.dto.ProjectResponseDTO;
import mvc.promiseme.project.entity.Member;
import mvc.promiseme.project.entity.Project;
import mvc.promiseme.project.repository.ProjectRepository;
import mvc.promiseme.todo.dto.TodoRequestDTO;
import mvc.promiseme.users.entity.Users;
import mvc.promiseme.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    @Override
    public List<ProjectResponseDTO> projectAll(Long userId) {

        return null;


    }
    @Override
    public List<String> categoryRanking() {
        return projectRepository.getCategoryRanking();
    }

    @Override
    public String insert(ProjectRequestDTO projectRequestDTO) {
        return null;
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
