package mvc.promiseme.meeting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.promiseme.calendar.dto.CalendarResponseDTO;
import mvc.promiseme.meeting.dto.MeetingResponseDTO;
import mvc.promiseme.meeting.service.MeetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins ="*", allowedHeaders = "*")
@RequestMapping("/meeting")
public class MeetingController {
    private final MeetingService meetingService;

    @GetMapping("/")
    public ResponseEntity<List<MeetingResponseDTO>>meetingAll(@RequestParam Long projectId){
        return ResponseEntity.ok(meetingService.meetingAll(projectId));

    }

}
