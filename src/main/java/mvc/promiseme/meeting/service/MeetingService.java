package mvc.promiseme.meeting.service;

import org.springframework.web.multipart.MultipartFile;

public interface MeetingService {
    void voiceToMeeting(MultipartFile multipartFile);
    void textToMeeting(String text);
}
