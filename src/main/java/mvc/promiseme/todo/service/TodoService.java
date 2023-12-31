package mvc.promiseme.todo.service;

import mvc.promiseme.todo.dto.TodoRequestDTO;
import mvc.promiseme.todo.dto.TodoResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface TodoService {
    public String insert (TodoRequestDTO todoRequestDTO);
    public String edit (TodoRequestDTO todoRequestDTO);
    public String check(Long todoId);
    public List<TodoResponseDTO> todoAll(Long projectId, Long userId, LocalDate todoDate);
}
