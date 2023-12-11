package mvc.promiseme.todo.dto;

import lombok.*;
import mvc.promiseme.todo.entity.ToDoStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TodoResponseDTO {
    private String content;
    private ToDoStatus isCompleted;
}
