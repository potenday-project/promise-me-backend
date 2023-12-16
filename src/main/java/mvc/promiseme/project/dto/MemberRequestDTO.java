package mvc.promiseme.project.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MemberRequestDTO {
    private String email;
    private String role;
}
