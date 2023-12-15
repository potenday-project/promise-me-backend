package mvc.promiseme.notice.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NoticeResponseDTO {
    private String nickname;
    private String content;
    private LocalDate createDate;
}
