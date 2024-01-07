package mvc.promiseme.common.utils;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.common.exception.ErrorCode;
import mvc.promiseme.common.exception.UserException;
import mvc.promiseme.project.entity.Project;
import mvc.promiseme.project.repository.ProjectRepository;
import mvc.promiseme.todo.entity.Todo;
import mvc.promiseme.todo.repository.TodoRepository;
import mvc.promiseme.users.entity.Users;
import mvc.promiseme.users.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class EntityLoaderById {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public Project getProjectByIdOrThrow(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("[ERROR] 해당 프로젝트는 존재하지 않습니다"));
    }

    public Users getUserByIdOrThrow(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorCode.INVALID_User_Login));
    }

    public Todo getTodoByIdOrThrow(Long todoId){
        return todoRepository.findById(todoId)
                .orElseThrow(() -> new NoSuchElementException("[ERROR] 해당 투두가 존재하지 않습니다."));
    }
}
