package mvc.promiseme.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RecommendMemberRequestDTO {
    private String category;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate start;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;
}
