package mvc.promiseme.todo;

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
import mvc.promiseme.todo.service.TodoServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class TodoServiceImplTest {

    @InjectMocks
    private TodoServiceImpl todoService;
    @Mock
    private TodoRepository todoRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private CalendarRepository calendarRepository;

    @Test
    void insertTodoTest() {
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO(1L, 2L, 3L, "Test Todo", LocalDate.parse("2023-12-14"));
        Project mockProject = new Project();
        Member mockMember = new Member();
        Calendar mockCalendar = new Calendar();
        Todo mockTodo = Todo.builder()
                .content(todoRequestDTO.getContent())
                .todoDate(todoRequestDTO.getTodoDate())
                .project(mockProject)
                .member(mockMember)
                .calendar(mockCalendar)
                .build();

        when(projectRepository.findById(1L)).thenReturn(Optional.of(mockProject));
        when(memberRepository.findById(2L)).thenReturn(Optional.of(mockMember));
        when(calendarRepository.findById(3L)).thenReturn(Optional.of(mockCalendar));
        when(todoRepository.save(Mockito.any(Todo.class))).thenReturn(mockTodo);

        String result = todoService.insert(todoRequestDTO);

        assertEquals("success", result);
    }

    @Test
    void checkTodoTest() {
        Todo mockTodo = Todo.builder()
                .toDoId(1L)
                .isCompleted(ToDoStatus.INCOMPLETE)
                .build();

        when(todoRepository.findById(1L)).thenReturn(Optional.of(mockTodo));
        when(todoRepository.save(Mockito.any(Todo.class))).thenReturn(mockTodo);

        String result = todoService.check(1L);

        assertEquals("success", result);
    }

    @Test
    void checkTodoNotFoundTest() {
        when(todoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> todoService.check(1L));
    }

    @Test
    void todoAllTest() {
        Member mockMember = new Member();
        List<Todo> mockTodoList = Collections.singletonList(Todo.builder().build());

        when(memberRepository.findById(1L)).thenReturn(Optional.of(mockMember));
        when(todoRepository.findByMemberAndAndTodoDate(Mockito.any(Member.class), Mockito.any(LocalDate.class)))
                .thenReturn(mockTodoList);

        List<TodoResponseDTO> result = todoService.todoAll(1L, 1L, LocalDate.now());
        assertEquals(mockTodoList.size(), result.size());
    }

    @Test
    void todoAllMemberNotFoundTest() {
        when(memberRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> todoService.todoAll(1L, 1L, LocalDate.now()));
    }

}
