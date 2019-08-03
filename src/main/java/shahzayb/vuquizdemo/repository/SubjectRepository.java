package shahzayb.vuquizdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shahzayb.vuquizdemo.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject getSubjectBySubjectCode(String subjectCode);
}
