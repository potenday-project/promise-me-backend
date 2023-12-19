package mvc.promiseme.project.repository;

import mvc.promiseme.project.entity.Member;
import mvc.promiseme.project.entity.Project;
import mvc.promiseme.project.entity.Role;
import mvc.promiseme.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsersAndProject(Users users, Project project);
    List<Member> findByUsers(Users users);
    List<Member> findByProjectAndAndRole(Project project, Role role);

}
