package mvc.promiseme.project.dto;

import lombok.*;
import mvc.promiseme.project.entity.Project;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProjectResponseDTO {
    private Long projectId;
    private String name;
    private LocalDate start;
    private LocalDate deadline;

}

