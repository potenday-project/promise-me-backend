package mvc.promiseme.todo.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TodoRequestDTO {
    private long projectId;
    private long memberId;
    private long calenderId;
    private String content;
    private LocalDate todoDate;
}
