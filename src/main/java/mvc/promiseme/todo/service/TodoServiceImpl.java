package mvc.promiseme.todo.service;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.project.entity.Member;
import mvc.promiseme.todo.dto.TodoRequestDTO;
import mvc.promiseme.todo.dto.TodoResponseDTO;
import mvc.promiseme.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements  TodoService{
    private final TodoRepository todoRepository;
    @Override
    public String insert(TodoRequestDTO todoRequestDTO) {
        return null;
    }

    @Override
    public String edit(TodoRequestDTO todoRequestDTO) {
        return null;
    }
    public String check(Long todoId){
        return null;

    }

    @Override
    public List<TodoResponseDTO> todoAll(Long memberId) {
        Member memeber = Member.builder().memberId(memberId).build();
        return todoRepository.findByMember(memeber);
    }
}
