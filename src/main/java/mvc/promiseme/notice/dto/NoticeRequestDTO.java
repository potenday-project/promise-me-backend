package mvc.promiseme.notice.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NoticeRequestDTO {
    private Long projectId;
    private Long memberId;
    private String content;
}
