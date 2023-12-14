package mvc.promiseme.todo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.promiseme.todo.dto.TodoRequestDTO;
import mvc.promiseme.todo.dto.TodoResponseDTO;
import mvc.promiseme.todo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/")
    public ResponseEntity<String> insertTodo(@RequestBody TodoRequestDTO todoRequestDTO){
        return ResponseEntity.ok(todoService.insert(todoRequestDTO));
    }
    @GetMapping("/")
    public ResponseEntity<List<TodoResponseDTO>> todoAll(@RequestParam(name = "projectId") Long projectId
            , @RequestParam(name = "userId") Long userId, @RequestParam(name = "todoDate") String todoDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(todoDate, formatter);
        return ResponseEntity.ok(todoService.todoAll(projectId, userId, localDate));
    }
    @PostMapping("/edit")
    public ResponseEntity<String> editTodo(@RequestBody TodoRequestDTO todoRequestDTO){
        return ResponseEntity.ok(todoService.edit(todoRequestDTO));
    }

    @PostMapping("/check")
    public ResponseEntity<String> checkTodo(@RequestBody Long todoId){
        return ResponseEntity.ok(todoService.check(todoId));
    }
}
