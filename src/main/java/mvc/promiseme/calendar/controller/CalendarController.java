package mvc.promiseme.calendar.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.promiseme.calendar.dto.CalendarResponseDTO;
import mvc.promiseme.calendar.service.CalendarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("calendar")
public class CalendarController {
    private final CalendarService calendarService;

    @GetMapping("/")
    public ResponseEntity<List<CalendarResponseDTO>>calendarAll(@RequestParam Long projectId){
        return ResponseEntity.ok(calendarService.calendarAll(projectId));

    }

}
