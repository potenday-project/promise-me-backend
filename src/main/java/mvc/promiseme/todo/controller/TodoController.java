package mvc.promiseme.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.promiseme.common.exception.ErrorResponse;
import mvc.promiseme.project.dto.ProjectResponseDTO;
import mvc.promiseme.todo.dto.TodoRequestDTO;
import mvc.promiseme.todo.dto.TodoResponseDTO;
import mvc.promiseme.todo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Tag(name = "Todo", description = "투두 API")
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @Operation(summary = "투두생성", description = "projectId, userId, 내용, 날짜를 입력 받아 Todo 생성한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "해당 프로젝트를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "해당 사용자를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "501", description = "알수 없는 에러로 투두를 생성할 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))


    })
    @PostMapping("/")
    public ResponseEntity<String> insertTodo(@RequestBody TodoRequestDTO todoRequestDTO){
        return ResponseEntity.ok(todoService.insert(todoRequestDTO));
    }
    @Operation(summary = "투두 조회", description = "projectId, userId, 날짜를 입력 받아 해당하는 TODO를 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "해당 프로젝트를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "해당 사용자를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),

    })
    @GetMapping("/")
    public ResponseEntity<List<TodoResponseDTO>> todoAll(@RequestParam(name = "projectId") Long projectId
            , @RequestParam(name = "userId") Long userId, @RequestParam(name = "todoDate") String todoDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(todoDate, formatter);
        return ResponseEntity.ok(todoService.todoAll(projectId, userId, localDate));
    }
    @Operation(summary = "투두 수정", description = "projectId, userId, 내용, 날짜를 입력 받아 Todo 수정한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "해당 프로젝트를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "해당 사용자를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "501", description = "알수 없는 에러로 투두를 생성할 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))


    })
    @PostMapping("/edit")
    public ResponseEntity<String> editTodo(@RequestBody TodoRequestDTO todoRequestDTO){
        return ResponseEntity.ok(todoService.edit(todoRequestDTO));
    }

    @Operation(summary = "투두생성", description = "해당 Todo 완료여부를 체크한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "해당 TODO를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),

    })
    @PostMapping("/check")
    public ResponseEntity<String> checkTodo(@RequestBody Map<String, Object> requestBody){
        Integer tmp = (Integer) requestBody.get("todoId");
        Long todoId = Long.valueOf(tmp);
        return ResponseEntity.ok(todoService.check(todoId));
    }
}
