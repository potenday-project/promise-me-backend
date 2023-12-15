package mvc.promiseme.project.repository;

import mvc.promiseme.project.entity.Member;
import mvc.promiseme.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByUsers(Users users);

}
