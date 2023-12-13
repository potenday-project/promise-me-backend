package mvc.promiseme.project.service;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.project.dto.ProjectRequestDTO;
import mvc.promiseme.project.repository.ProjectRepository;
import mvc.promiseme.todo.dto.TodoRequestDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    @Override
    public String insert(ProjectRequestDTO projectRequestDTO) {
        return null;
    }

    @Override
    public Map<String,Integer> dday(Long projectId) {
        Map<String,Integer> ddayResult = new HashMap<>();
        ddayResult.put("Dday", projectRepository.finddday(projectId));
        return ddayResult;
    }
}
