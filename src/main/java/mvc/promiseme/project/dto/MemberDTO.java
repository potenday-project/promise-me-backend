package mvc.promiseme.project.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MemberDTO {
    private Long userId;
    private String role;
}
