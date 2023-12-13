package mvc.promiseme.project.service;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.project.dto.ProjectRequestDTO;
import mvc.promiseme.project.repository.ProjectRepository;
import mvc.promiseme.todo.dto.TodoRequestDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    @Override
    public String insert(ProjectRequestDTO projectRequestDTO) {
        return null;
    }

    @Override
    public int dday(Long projectId) {
        return projectRepository.finddday(projectId);
    }
}
