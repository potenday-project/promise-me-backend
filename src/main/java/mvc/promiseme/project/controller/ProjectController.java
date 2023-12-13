package mvc.promiseme.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.promiseme.project.dto.ProjectRequestDTO;
import mvc.promiseme.project.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins ="*", allowedHeaders = "*")
@RequestMapping("project")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/dday")
    public int dday(@RequestParam(name ="projectId") Long projectId){
        return projectService.dday(projectId);
    }


}
