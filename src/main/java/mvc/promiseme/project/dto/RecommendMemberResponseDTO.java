package mvc.promiseme.project.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RecommendMemberResponseDTO {
    private String role;
    private String description;
}
