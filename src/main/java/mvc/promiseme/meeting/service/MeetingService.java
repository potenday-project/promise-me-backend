package mvc.promiseme.meeting.service;

import mvc.promiseme.meeting.dto.MeetingResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MeetingService {
    void voiceToMeeting(MultipartFile multipartFile, Long projectId);
    void textToMeeting(String text, Long projectId);
    public List<MeetingResponseDTO> meetingAll(Long projectId);
}
