package shahzayb.vuquizdemo.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant instant = Instant.now();
    private Integer result = 0;
    private Integer totalMarks;

    @Transient
    private Integer curQuestionNumber = 1;

    @Transient
    private List<Question> questions;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "subject")
    private Subject subject;

    public Quiz() {
    }

    public Quiz(Long id, Instant instant, Integer result, Integer totalMarks, Integer curQuestionNumber, List<Question> questions, User user, Subject subject) {
        this.id = id;
        this.instant = instant;
        this.result = result;
        this.totalMarks = totalMarks;
        this.curQuestionNumber = curQuestionNumber;
        this.questions = questions;
        this.user = user;
        this.subject = subject;
    }

    public void addResult(Integer result) {
        if (this.result == null) {
            this.result = result;
        } else {
            this.result += result == null ? 0 : result;
        }
    }

    public Question getCurrentQuestion() {
        return questions.get(curQuestionNumber - 1);
    }

    @PrePersist
    public void calculateTotalMarks() {
        totalMarks = questions.stream().mapToInt(Question::getTotalMarks).sum();
    }

}
