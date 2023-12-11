package mvc.promiseme.todo.dto;

import lombok.*;

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
}
