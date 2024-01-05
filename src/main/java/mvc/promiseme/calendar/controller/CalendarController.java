package mvc.promiseme.calendar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.promiseme.calendar.dto.CalendarAndTodoAllByRoleDto;
import mvc.promiseme.calendar.dto.CalendarResponseDTO;
import mvc.promiseme.calendar.service.CalendarService;
import mvc.promiseme.common.exception.ErrorResponse;
import mvc.promiseme.project.dto.ProjectResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@Tag(name = "Calendar", description = "일정 API")
@RequestMapping("/calendar")
public class CalendarController {
    private final CalendarService calendarService;

//    @GetMapping("/")
//    public ResponseEntity<List<CalendarResponseDTO>>calendarAll(@RequestParam Long projectId){
//        return ResponseEntity.ok(calendarService.calendarAll(projectId));
//
//    }

    @Operation(summary = "메인페이지", description = "프로젝트에 관련된 일정, todo 모두 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = CalendarAndTodoAllByRoleDto.class))),
            @ApiResponse(responseCode = "401", description = "프로젝트를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))

    })
    @GetMapping("/todoAll")
    public ResponseEntity<List<CalendarAndTodoAllByRoleDto>> calendarTodoAll(
            @RequestParam(name = "projectId") Long projectId
            , @RequestParam(name = "todoDate") String todoDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(todoDate, formatter);
        return ResponseEntity.ok(calendarService.calendarAndtodoAll(projectId, localDate));
    }

}