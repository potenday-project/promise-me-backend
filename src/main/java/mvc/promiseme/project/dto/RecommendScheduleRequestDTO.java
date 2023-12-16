package mvc.promiseme.project.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RecommendScheduleRequestDTO {
    private Long projectId;
    private String category;
    private String member;
    private LocalDate start;
    private LocalDate deadline;
}
