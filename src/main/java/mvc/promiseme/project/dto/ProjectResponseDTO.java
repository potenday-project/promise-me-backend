package mvc.promiseme.project.dto;

import lombok.*;
import mvc.promiseme.project.entity.Project;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProjectResponseDTO {
    private Long projectId;
    private String name;
    private String role;

}

