package mvc.promiseme.meeting.service;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.calendar.dto.CalendarResponseDTO;
import mvc.promiseme.common.NaverKey;
import mvc.promiseme.meeting.dto.MeetingResponseDTO;
import mvc.promiseme.meeting.repository.MeetingRepository;
import mvc.promiseme.project.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MeetingServiceImpl implements MeetingService {
    private final MeetingRepository meetingRepository;

    private final NaverKey naverKey;

    @Override
    public List<MeetingResponseDTO> meetingAll(Long projectId) {
        Project project = Project.builder().projectId(projectId).build();
        return meetingRepository.findByProject(project);
    }
}
