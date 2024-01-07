package mvc.promiseme.common.utils;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.project.entity.Project;
import mvc.promiseme.project.repository.ProjectRepository;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class EntityLoaderById {

    private final ProjectRepository projectRepository;

    public Project getProjectByIdOrThrow(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("[ERROR] 해당 프로젝트는 존재하지 않습니다"));
    }
}
