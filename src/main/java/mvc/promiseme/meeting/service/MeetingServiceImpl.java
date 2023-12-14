package mvc.promiseme.meeting.service;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.meeting.exception.FileUploadException;
import mvc.promiseme.meeting.exception.SummaryException;
import mvc.promiseme.meeting.exception.TransferTextException;
import mvc.promiseme.meeting.dto.MeetingResponseDTO;
import mvc.promiseme.meeting.repository.MeetingRepository;
import mvc.promiseme.project.entity.Project;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MeetingServiceImpl implements MeetingService {
    private final MeetingRepository meetingRepository;
    private final FileUploadingService fileUploadingService;
    private final TransferTextService transferTextService;
    private final SummaryTextService summaryTextService;

    @Override
    public List<MeetingResponseDTO> meetingAll(Long projectId) {
        Project project = Project.builder().projectId(projectId).build();
        return meetingRepository.findByProject(project);
    }

    @Override
    public void voiceToMeeting(MultipartFile multipartFile) {
        String uploadURL = fileUploadingService.fileUploadToCloud(multipartFile);
        if(uploadURL == null) throw new FileUploadException("[ERROR] file upload fail");
        String transferredText = transferTextService.transferText(uploadURL);
        if(transferredText == null) throw new TransferTextException("[ERROR] transfer voice to text fail");
        String summary = summaryTextService.summary(transferredText);
        if(summary == null) throw new SummaryException("[ERROR] summary fail");
    }

    @Override
    public void textToMeeting(String text) {
        String summary = summaryTextService.summary(text);
        if(summary == null) throw new SummaryException("[ERROR] summary fail");
    }


}
