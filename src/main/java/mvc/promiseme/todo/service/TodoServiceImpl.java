package mvc.promiseme.todo.service;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.common.utils.EntityLoaderById;
import mvc.promiseme.project.entity.Member;
import mvc.promiseme.project.entity.Project;
import mvc.promiseme.project.repository.MemberRepository;
import mvc.promiseme.todo.dto.TodoRequestDTO;
import mvc.promiseme.todo.dto.TodoResponseDTO;
import mvc.promiseme.todo.entity.Todo;
import mvc.promiseme.todo.repository.TodoRepository;
import mvc.promiseme.users.entity.Users;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    private final EntityLoaderById entityLoaderById;

    @Override
    public String insert(TodoRequestDTO todoRequestDTO) {
        Member member = getMember(todoRequestDTO.getProjectId(), todoRequestDTO.getUserId());
        Project project = entityLoaderById.getProjectByIdOrThrow(todoRequestDTO.getProjectId());

        Todo t = new Todo();
        Todo todo = t.mapToEntity(todoRequestDTO, project, member);
        todoRepository.save(todo);
        return "success";
    }

    @Override
    public String edit(TodoRequestDTO todoRequestDTO) {
        return null;
    }

    @Override
    public String check(Long todoId){
        Todo todo = entityLoaderById.getTodoByIdOrThrow(todoId);
        todo = updateStatus(todo);
        todoRepository.save(todo);
        return "success";
    }

    private Todo updateStatus(Todo todo){
        if(todo.isCompleted() == true) todo.setCompleted(false);
        else if (todo.isCompleted() == false) todo.setCompleted(true);
        return todo;
    }

    @Override
    public List<TodoResponseDTO> todoAll(Long projectId, Long userId, LocalDate todoDate) {
        Member member = getMember(projectId, userId);
        List<Todo> todoList = todoRepository.findByMemberAndAndTodoDate(member, todoDate);
        if(todoList == null) throw new NoSuchElementException("[ERROR] 해당 데이터가 존재하지 않습니다.");
        TodoResponseDTO todoResponseDTO = new TodoResponseDTO();
        return todoResponseDTO.convertToDtoList(todoList);
    }
    
    private Member getMember(Long projectId, Long userId){
        Users users = entityLoaderById.getUserByIdOrThrow(userId);
        Project project = entityLoaderById.getProjectByIdOrThrow(projectId);

        return memberRepository.findByUsersAndProject(users, project)
                .orElseThrow(() -> new NoSuchElementException("[ERROR] 해당 프로젝트 멤버가 존재하지 않습니다."));
    }
}
