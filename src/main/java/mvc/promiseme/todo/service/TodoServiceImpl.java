package mvc.promiseme.todo.service;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.calendar.entity.Calendar;
import mvc.promiseme.calendar.repository.CalendarRepository;
import mvc.promiseme.project.entity.Member;
import mvc.promiseme.project.entity.Project;
import mvc.promiseme.project.repository.MemberRepository;
import mvc.promiseme.project.repository.ProjectRepository;
import mvc.promiseme.todo.dto.TodoRequestDTO;
import mvc.promiseme.todo.dto.TodoResponseDTO;
import mvc.promiseme.todo.entity.ToDoStatus;
import mvc.promiseme.todo.entity.Todo;
import mvc.promiseme.todo.repository.TodoRepository;
import mvc.promiseme.users.entity.Users;
import mvc.promiseme.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements  TodoService{

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;

    @Override
    public String insert(TodoRequestDTO todoRequestDTO) {
        Member member = getMember(todoRequestDTO.getProjectId(), todoRequestDTO.getUserId());
        Project project = projectRepository.findById(todoRequestDTO.getProjectId())
                .orElseThrow(() -> new NoSuchElementException("[ERROR] 해당 프로젝트가 존재하지 않습니다."));

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
    public List<TodoResponseDTO> todoAll(Long projectId, Long userId, LocalDate todoDate) {
        Member member = getMember(projectId, userId);
        List<Todo> todoList = todoRepository.findByMemberAndAndTodoDate(member, todoDate);
        if(todoList == null) throw new NoSuchElementException("[ERROR] 해당 데이터가 존재하지 않습니다.");
        TodoResponseDTO todoResponseDTO = new TodoResponseDTO();
        return todoResponseDTO.convertToDtoList(todoList);
    }
    
    private Member getMember(Long projectId, Long userId){
        Users users = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("[ERROR] 해당 회원이 존재하지 않습니다."));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("[ERROR] 해당 프로젝트가 존재하지 않습니다."));
        return memberRepository.findByUsersAndProject(users, project)
                .orElseThrow(() -> new NoSuchElementException("[ERROR] 해당 프로젝트 멤버가 존재하지 않습니다."));
    }
}
