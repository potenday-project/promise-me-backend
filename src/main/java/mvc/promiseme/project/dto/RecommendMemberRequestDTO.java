package mvc.promiseme.project.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RecommendMemberRequestDTO {
    private String category;
    private LocalDate start;
    private LocalDate deadline;
}
