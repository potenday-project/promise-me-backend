package mvc.promiseme.meeting.dto;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SummaryRequestDTO {
    private String meetingContent;
    private Long projectId;
}
