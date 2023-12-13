package mvc.promiseme.project.service;

import mvc.promiseme.project.dto.ProjectRequestDTO;

import java.util.Map;

public interface ProjectService {
    public String insert (ProjectRequestDTO projectRequestDTO);
    public int dday (Long projectId);
}
