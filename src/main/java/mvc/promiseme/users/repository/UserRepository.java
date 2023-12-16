package mvc.promiseme.users.repository;

import mvc.promiseme.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmailIgnoreCase(String email);
    Users findUsersByUserId(Long userId);
}
