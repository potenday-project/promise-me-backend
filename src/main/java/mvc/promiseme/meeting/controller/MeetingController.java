package mvc.promiseme.meeting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.promiseme.meeting.dto.MeetingResponseDTO;
import mvc.promiseme.meeting.service.FileUploadingService;
import mvc.promiseme.meeting.service.MeetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<List<MeetingResponseDTO>> meetingAll(@RequestParam Long projectId){
        return ResponseEntity.ok(meetingService.meetingAll(projectId));
    }

    @PostMapping("/transfer")
    public void transferVoice(@RequestParam("voiceFile") MultipartFile multipartFile){
        meetingService.voiceToMeeting(multipartFile);
    }

    @PostMapping("/summary")
    public void summaryText(@RequestParam("text") String text){
        meetingService.textToMeeting(text);
    }
}
