package shahzayb.vuquizdemo.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import shahzayb.vuquizdemo.entity.Quiz;
import shahzayb.vuquizdemo.entity.User;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> getByUser(User user, Sort sort);
}
