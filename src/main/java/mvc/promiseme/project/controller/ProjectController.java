package mvc.promiseme.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.promiseme.project.dto.ProjectRequestDTO;
import mvc.promiseme.project.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins ="*", allowedHeaders = "*")
@RequestMapping("project")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/dday")
    public ResponseEntity <Map<String,Integer>> dday(@RequestParam Long projectId){
        Map<String,Integer> ddayResult = new HashMap<>();
        ddayResult.put("Dday", projectService.dday(projectId));

        return ResponseEntity.ok(ddayResult);
    }


}
