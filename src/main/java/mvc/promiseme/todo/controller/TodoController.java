package mvc.promiseme.todo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.promiseme.todo.dto.TodoRequestDTO;
import mvc.promiseme.todo.dto.TodoResponseDTO;
import mvc.promiseme.todo.service.TodoService;
import mvc.promiseme.users.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("todo")
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/")
    public ResponseEntity<String> insertTodo(@RequestBody TodoRequestDTO todoRequestDTO){
        return ResponseEntity.ok(todoService.insert(todoRequestDTO));
    }
    @GetMapping("/")
    public ResponseEntity<List<TodoResponseDTO>> todoAll(@RequestBody Long memberId){
        return ResponseEntity.ok(todoService.todoAll(memberId));
    }
    @PostMapping("/edit")
    public ResponseEntity<String> editTodo(@RequestBody TodoRequestDTO todoRequestDTO){
        return ResponseEntity.ok(todoService.edit(todoRequestDTO));
    }

    @PostMapping("/check")
    public ResponseEntity<String> editTodo(@RequestBody Long todoId){
        return ResponseEntity.ok(todoService.check(todoId));
    }
}
