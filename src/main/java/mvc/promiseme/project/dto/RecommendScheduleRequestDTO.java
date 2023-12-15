package mvc.promiseme.project.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RecommendScheduleRequestDTO {
    private String category;
    private String topic;
    private String member;
    private LocalDate start;
    private LocalDate deadline;
}
