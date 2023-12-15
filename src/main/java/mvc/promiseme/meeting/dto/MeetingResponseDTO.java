package mvc.promiseme.meeting.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MeetingResponseDTO {
    private Long meetingId;
    private String summary;
    private String meetingContent;
    private LocalDate meetingDate;
    private String meetingName;
}
