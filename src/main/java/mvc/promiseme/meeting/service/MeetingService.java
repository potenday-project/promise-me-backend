package mvc.promiseme.meeting.service;

import mvc.promiseme.meeting.dto.MeetingResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MeetingService {
    String voiceToMeeting(MultipartFile multipartFile, Long projectId);
    String textToMeeting(String text, Long projectId);
    public List<MeetingResponseDTO> meetingAll(Long projectId);
}
