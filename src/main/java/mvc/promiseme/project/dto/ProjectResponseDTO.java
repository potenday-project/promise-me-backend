package mvc.promiseme.project.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProjectResponseDTO {
    private Long projectId;
    private String projectName;
    private String role;
}
