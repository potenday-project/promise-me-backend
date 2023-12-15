package mvc.promiseme.project.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProjectRequestDTO {
    private String name;
    private String category;
    private String topic;
    private LocalDate start;
    private LocalDate deadline;
}
