package mvc.promiseme.calendar.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.promiseme.calendar.dto.CalendarAndTodoAllByRoleDto;
import mvc.promiseme.calendar.dto.CalendarResponseDTO;
import mvc.promiseme.calendar.service.CalendarService;
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
@RequestMapping("/calendar")
public class CalendarController {
    private final CalendarService calendarService;

//    @GetMapping("/")
//    public ResponseEntity<List<CalendarResponseDTO>>calendarAll(@RequestParam Long projectId){
//        return ResponseEntity.ok(calendarService.calendarAll(projectId));
//
//    }

    @GetMapping("/todoAll")
    public ResponseEntity<List<CalendarAndTodoAllByRoleDto>> calendarTodoAll(
            @RequestParam(name = "projectId") Long projectId
            , @RequestParam(name = "todoDate") String todoDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(todoDate, formatter);
        return ResponseEntity.ok(calendarService.calendarAndtodoAll(projectId, localDate));
    }

}