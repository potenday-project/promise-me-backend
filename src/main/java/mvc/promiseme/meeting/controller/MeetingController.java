package mvc.promiseme.meeting.controller;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.meeting.service.FileUploadingService;
import mvc.promiseme.meeting.service.MeetingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/meeting")
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;

    @PostMapping("/transfer")
    public void transferVoice(@RequestParam("voiceFile") MultipartFile multipartFile){
        meetingService.voiceToMeeting(multipartFile);
    }

    @GetMapping("/callback")
    public void callback(){}
}
