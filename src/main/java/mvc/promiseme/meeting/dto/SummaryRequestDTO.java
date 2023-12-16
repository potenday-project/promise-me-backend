package mvc.promiseme.meeting.dto;

import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SummaryRequestDTO {
    private String meetingContent;
    private Long projectId;
}
