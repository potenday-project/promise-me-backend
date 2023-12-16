package mvc.promiseme.todo;

import mvc.promiseme.project.entity.Member;
import mvc.promiseme.project.entity.Project;
import mvc.promiseme.project.repository.MemberRepository;
import mvc.promiseme.project.repository.ProjectRepository;
import mvc.promiseme.todo.dto.TodoRequestDTO;
import mvc.promiseme.todo.repository.TodoRepository;
import mvc.promiseme.todo.service.TodoService;
import mvc.promiseme.todo.service.TodoServiceImpl;
import mvc.promiseme.users.entity.Users;
import mvc.promiseme.users.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TodoServiceImplTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TodoServiceImpl todoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void insert_todoSuccessfully() {
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO();
        todoRequestDTO.setProjectId(1L);
        todoRequestDTO.setUserId(1L);

        when(projectRepository.findById(any())).thenReturn(Optional.of(new Project()));
        when(memberRepository.findByUsersAndProject(any(), any())).thenReturn(Optional.of(new Member()));
        when(userRepository.findById(any())).thenReturn(Optional.of(new Users()));

        assertEquals("success", todoService.insert(todoRequestDTO));
        verify(todoRepository, times(1)).save(any());
    }

    @Test
    void insert_projectNotFound() {
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO();
        todoRequestDTO.setProjectId(1L);
        todoRequestDTO.setUserId(1L);

        when(projectRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> todoService.insert(todoRequestDTO));
        verify(todoRepository, never()).save(any());
    }

    @Test
    void insert_memberNotFound() {
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO();
        todoRequestDTO.setProjectId(1L);
        todoRequestDTO.setUserId(1L);

        when(projectRepository.findById(any())).thenReturn(Optional.of(new Project()));
        when(memberRepository.findByUsersAndProject(any(), any())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> todoService.insert(todoRequestDTO));
        verify(todoRepository, never()).save(any());
    }

    @Test
    void insert_userNotFound() {
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO();
        todoRequestDTO.setProjectId(1L);
        todoRequestDTO.setUserId(1L);

        when(projectRepository.findById(any())).thenReturn(Optional.of(new Project()));
        when(memberRepository.findByUsersAndProject(any(), any())).thenReturn(Optional.of(new Member()));
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> todoService.insert(todoRequestDTO));
        verify(todoRepository, never()).save(any());
    }
    @Test
    public void getMember(){
        TodoRequestDTO todoRequestDTO = TodoRequestDTO.builder().todoDate(LocalDate.parse("2023-12-17")).projectId(3L).content("잠자기").userId(1L).build();
        todoService.insert(todoRequestDTO);
    }
}
