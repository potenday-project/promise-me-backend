package mvc.promiseme.meeting.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MeetingResponseDTO {
    private Long meetingId;
    private String summary;
    private String meetingContent;
    private LocalDateTime meetingDate;
//    public MeetingResponseDTO(Long meetingId, String summary, String meetingContent, LocalDateTime meetingDate) {
//        // 생성자 내용
//        this.meetingId = meetingId;
//        this.summary = summary;
//        this.meetingContent = meetingContent;
//        this.meetingDate = meetingDate;
//    }
}
