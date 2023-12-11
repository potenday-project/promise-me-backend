package mvc.promiseme.meeting.service;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.common.NaverKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class MeetingServiceImpl implements MeetingService {

    private final FileUploadingService fileUploadingService;

    @Override
    public void voiceToMeeting(MultipartFile multipartFile) {
        fileUploadingService.fileUploadToCloud(multipartFile);
    }
}
