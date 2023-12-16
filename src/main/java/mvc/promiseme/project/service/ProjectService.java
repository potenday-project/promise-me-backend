package mvc.promiseme.project.service;

import mvc.promiseme.project.dto.ProjectRequestDTO;
import mvc.promiseme.project.dto.ProjectResponseDTO;

import java.util.List;

public interface ProjectService {
    public List<ProjectResponseDTO> projectAll(Long userId);
    public List<String> categoryRanking();

    public Long insert (ProjectRequestDTO projectRequestDTO);
    public int progress(Long projectId);
    public int dday (Long projectId);

}
