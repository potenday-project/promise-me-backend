package mvc.promiseme.meeting.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MeetingResponseDTO {
    private String meetingId;
    private String summary;
    private String meetingContent;
    private String meetingDate;
}
