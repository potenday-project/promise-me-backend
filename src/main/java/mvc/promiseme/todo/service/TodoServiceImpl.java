package mvc.promiseme.todo.service;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.project.entity.Member;
import mvc.promiseme.project.repository.MemberRepository;
import mvc.promiseme.project.repository.ProjectRepository;
import mvc.promiseme.todo.dto.TodoRequestDTO;
import mvc.promiseme.todo.dto.TodoResponseDTO;
import mvc.promiseme.todo.entity.ToDoStatus;
import mvc.promiseme.todo.entity.Todo;
import mvc.promiseme.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements  TodoService{

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    @Override
    public String insert(TodoRequestDTO todoRequestDTO) {
        return null;
    }

    @Override
    public String edit(TodoRequestDTO todoRequestDTO) {
        return null;
    }

    @Override
    public String check(Long todoId){
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new NoSuchElementException("[ERROR] 해당 투두가 존재하지 않습니다."));
        todo = updateStatus(todo);
        todoRepository.save(todo);
        return "success";
    }

    private Todo updateStatus(Todo todo){
        ToDoStatus status = todo.getIsCompleted();
        if(status.equals(ToDoStatus.COMPLETE)) todo.setIsCompleted(ToDoStatus.INCOMPLETE);
        else if (status.equals(ToDoStatus.INCOMPLETE)) todo.setIsCompleted(ToDoStatus.COMPLETE);
        return todo;
    }

    @Override
    public List<TodoResponseDTO> todoAll(Long memberId, LocalDate todoDate) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("[ERROR] 해당 멤버가 존재하지 않습니다."));
        List<Todo> todoList = todoRepository.findByMemberAndAndTodoDate(member, todoDate);
        if(todoList == null) throw new NoSuchElementException("[ERROR] 해당 데이터가 존재하지 않습니다.");
        TodoResponseDTO todoResponseDTO = new TodoResponseDTO();
        return todoResponseDTO.convertToDtoList(todoList);
    }
}
