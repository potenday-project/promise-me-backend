package mvc.promiseme.meeting.service;

import mvc.promiseme.meeting.dto.MeetingResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MeetingService {
    public List<MeetingResponseDTO> meetingAll(Long projectId);
}
