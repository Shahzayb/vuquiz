package shahzayb.vuquizdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shahzayb.vuquizdemo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
