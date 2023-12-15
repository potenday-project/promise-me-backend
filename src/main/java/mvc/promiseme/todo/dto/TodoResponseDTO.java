package mvc.promiseme.todo.dto;

import lombok.*;
import mvc.promiseme.todo.entity.Todo;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TodoResponseDTO {

    private Long todoId;
    private String content;
    private boolean isCompleted;

    public List<TodoResponseDTO> convertToDtoList(List<Todo> todoList) {
        return todoList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private TodoResponseDTO convertToDto(Todo todo) {
        return TodoResponseDTO.builder()
                .todoId(todo.getToDoId())
                .content(todo.getContent())
                .isCompleted(todo.isCompleted())
                .build();
    }
}
