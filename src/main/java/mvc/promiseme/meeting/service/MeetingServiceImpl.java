package mvc.promiseme.meeting.service;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.common.NaverKey;
import mvc.promiseme.meeting.exceotion.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class MeetingServiceImpl implements MeetingService {

    private final FileUploadingService fileUploadingService;
    private final TransferTextService transferTextService;

    @Override
    public void voiceToMeeting(MultipartFile multipartFile) {
        String uploadURL = fileUploadingService.fileUploadToCloud(multipartFile);
        if(uploadURL == null) throw new FileUploadException("[ERROR] file upload fail");
        transferTextService.transferText(uploadURL);
    }
}
