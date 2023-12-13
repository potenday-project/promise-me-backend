package mvc.promiseme.users.repository;

import mvc.promiseme.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    Users getUsersByUserId(Long userId);
}
