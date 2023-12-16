package mvc.promiseme.meeting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.promiseme.meeting.dto.MeetingResponseDTO;
import mvc.promiseme.meeting.dto.SummaryRequestDTO;
import mvc.promiseme.meeting.service.MeetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins ="*", allowedHeaders = "*")
@RequestMapping("/meeting")
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;

    @GetMapping("/")
    public ResponseEntity<List<MeetingResponseDTO>> meetingAll(@RequestParam(name="projectId") Long projectId){
        return ResponseEntity.ok(meetingService.meetingAll(projectId));
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferVoice(@RequestParam("voiceFile") MultipartFile multipartFile, @RequestParam("projectId") Long projectId){
        return ResponseEntity.ok(meetingService.voiceToMeeting(multipartFile, projectId));
    }

    @PostMapping("/summary")
    public ResponseEntity<String> summaryText(@RequestBody SummaryRequestDTO summaryRequestDTO) {
        return ResponseEntity.ok(meetingService.textToMeeting(summaryRequestDTO.getMeetingContent(), summaryRequestDTO.getProjectId()));
    }
}
