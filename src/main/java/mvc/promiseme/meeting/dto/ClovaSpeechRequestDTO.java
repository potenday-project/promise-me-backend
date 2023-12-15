package mvc.promiseme.meeting.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClovaSpeechRequestDTO {
    private String language;
    private String callback;
}
