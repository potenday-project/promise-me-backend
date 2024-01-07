package mvc.promiseme.meeting.service;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.common.utils.EntityLoaderById;
import mvc.promiseme.meeting.entity.Meeting;
import mvc.promiseme.meeting.exception.FileUploadException;
import mvc.promiseme.meeting.exception.SummaryException;
import mvc.promiseme.meeting.exception.TransferTextException;
import mvc.promiseme.meeting.dto.MeetingResponseDTO;
import mvc.promiseme.meeting.repository.MeetingRepository;
import mvc.promiseme.project.entity.Project;
import mvc.promiseme.project.repository.ProjectRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;
    private final ProjectRepository projectRepository;

    private final FileUploadingService fileUploadingService;
    private final TransferTextService transferTextService;
    private final SummaryTextService summaryTextService;

    private final EntityLoaderById entityLoaderById;

    @Override
    public List<MeetingResponseDTO> meetingAll(Long projectId) {
        Project project = Project.builder().projectId(projectId).build();
        return meetingRepository.findByProject(project);
    }

    @Async
    @Override
    public String voiceToMeeting(MultipartFile multipartFile, Long projectId) {
        Meeting meeting = addEmptyRecord(projectId);

        String uploadURL = fileUploadingService.fileUploadToCloud(multipartFile);
        if(uploadURL == null) throw new FileUploadException("[ERROR] file upload fail");
        String transferredText = transferTextService.transferText(uploadURL);
        if(transferredText == null) throw new TransferTextException("[ERROR] transfer voice to text fail");
        String summary = summaryTextService.summary(transferredText);
        if(summary == null) throw new SummaryException("[ERROR] summary fail");

        String meetingName = summaryTextService.getTitle(summary);
        updateEmptyRecord(meeting, transferredText, meetingName,summary);
        return "success";
    }

    @Override
    public String textToMeeting(String text, Long projectId) {
        Meeting meeting = addEmptyRecord(projectId);
        String summary = summaryTextService.summary(text);
        if(summary == null) throw new SummaryException("[ERROR] summary fail");

        String meetingName = summaryTextService.getTitle(summary);
        updateEmptyRecord(meeting, text,meetingName, summary);
        return "success";
    }

    private Meeting addEmptyRecord(Long projectId){
        Project project = entityLoaderById.getProjectByIdOrThrow(projectId);

        Meeting meeting = Meeting.builder()
                .project(project)
                .build();
        meetingRepository.save(meeting);
        return meeting;
    }

    private void updateEmptyRecord(Meeting meeting, String content,String meetingName, String summary){
        meeting.setMeetingContent(content);
        meeting.setSummary(summary);
        meeting.setMeetingName(meetingName);
        meetingRepository.save(meeting);
    }


}
