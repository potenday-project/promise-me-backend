package mvc.promiseme.todo.repository;

import mvc.promiseme.project.entity.Member;
import mvc.promiseme.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    List<Todo> findByMemberAndAndTodoDate(Member member, LocalDate todoDate);
}
