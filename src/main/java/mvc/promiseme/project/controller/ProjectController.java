package mvc.promiseme.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.promiseme.project.dto.ProjectRequestDTO;
import mvc.promiseme.project.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("project")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/")
    public ResponseEntity<String> insertTodo(@RequestBody ProjectRequestDTO projectRequestDTO){
        return ResponseEntity.ok(projectService.insert(projectRequestDTO));
    }


}
