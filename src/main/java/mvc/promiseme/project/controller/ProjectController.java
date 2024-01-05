package mvc.promiseme.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.promiseme.common.exception.ErrorCode;
import mvc.promiseme.common.exception.ErrorResponse;
import mvc.promiseme.project.dto.ProjectRequestDTO;
import mvc.promiseme.project.dto.ProjectResponseDTO;
import mvc.promiseme.project.dto.RecommendMemberRequestDTO;
import mvc.promiseme.project.dto.RecommendScheduleRequestDTO;
import mvc.promiseme.project.service.ProjectService;
import mvc.promiseme.project.service.RecommendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Tag(name = "Project", description = "프로젝트 API")
@RequiredArgsConstructor
@CrossOrigin(origins ="*", allowedHeaders = "*")
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;
    private final RecommendService recommendService;
    @GetMapping("/")
    @Operation(summary = "전체 프로젝트조회", description = "user가 포함된 모든 프로젝트를 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ProjectResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "프로젝트를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))

    })
    public ResponseEntity<List<ProjectResponseDTO>> projectAll(@Parameter(description = "유저ID", required = true) @RequestParam("userId") Long userId){
        return ResponseEntity.ok(projectService.projectAll(userId));

    }
    @GetMapping("/category")
    @Operation(summary = "상위 카테고리 조회", description = "현재 존재하는 프로젝트 카테고리중 많은 순으로 6개 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "501", description = "존재하는 프로젝트가 없어서 카테고리를 가져올 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))

    })
    public ResponseEntity<List<String>> categoryRanking(){
//        if(projectService.categoryRanking()==null)
//            return ResponseEntity.status(ErrorCode.CATEGORY_SERVER_ERROR.getStatus()).body(ErrorCode.CATEGORY_SERVER_ERROR.getMessage());

        return ResponseEntity.ok(projectService.categoryRanking());

    }

    @Operation(summary = "프로젝트 생성", description = "새로운 프로젝트 생성")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "501", description = "프로젝트 생성 중 알 수 없는 에러가 발생하였습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))

    })
    @PostMapping("/create")
    public ResponseEntity<Map<String, Long>> insert(@RequestBody ProjectRequestDTO projectRequestDTO){
        Map<String, Long> result = new HashMap<>();


            result.put("projectId",projectService.insert(projectRequestDTO));
        return ResponseEntity.ok(result);
    }
    @Operation(summary = "프로젝트 진행척도", description = "현재 프로젝트가 얼마나 진행되었는지 퍼센트로 확인할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "401", description = "해당 프로젝트를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))

    })
    @GetMapping("/progress")
    public ResponseEntity<Map<String, Integer>> progress(@RequestParam("projectId")Long projectId){
        Map<String,Integer> progressResult = new HashMap<>();
        progressResult.put("progress", projectService.progress(projectId));
        return ResponseEntity.ok(progressResult);
    }
    @Operation(summary = "프로젝트 디데이", description = "해당하는 프로젝트의 디데이를 계산한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "401", description = "프로젝트를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))

    })
    @GetMapping("/dday")
    public ResponseEntity <Map<String,Integer>> dday(@RequestParam("projectId") Long projectId){
        Map<String,Integer> ddayResult = new HashMap<>();
        ddayResult.put("Dday", projectService.dday(projectId));

        return ResponseEntity.ok(ddayResult);
    }

    @Operation(summary = "멤버 추천", description = "입력한 값에 맞는 프로젝트 역할과 할일을 보여준다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "401", description = "프로젝트를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))

    })
    @PostMapping("/recommend/member")
    public ResponseEntity<Map<String,String>> recommendMember(@RequestBody RecommendMemberRequestDTO recommendMemberRequestDTO){
        return ResponseEntity.ok(recommendService.recommendMember(recommendMemberRequestDTO));
    }

    @Operation(summary = "프로젝트 일정 추천", description = "입력한 값에 맞는 프로젝트 역할과 할일을 보여준다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ProjectResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "프로젝트를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))

    })
    @PostMapping("/recommend/schedule")
    public ResponseEntity<List<Map<String, String>>> recommendSchedule(@RequestBody RecommendScheduleRequestDTO recommendScheduleRequestDTO){
        return ResponseEntity.ok(recommendService.recommendSchedule(recommendScheduleRequestDTO));
    }

}
