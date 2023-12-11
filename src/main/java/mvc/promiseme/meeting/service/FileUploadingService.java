package mvc.promiseme.meeting.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadingService {
    String fileUploadToCloud(MultipartFile voiceFile);
}
