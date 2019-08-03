package shahzayb.vuquizdemo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private Integer totalMarks;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<QuestionOption> questionOptions;

    @OneToOne
    @JoinColumn(name = "correct_answer")
    private QuestionOption CorrectAnswer;

    @ManyToOne
    @JoinColumn(name = "subject")
    private Subject subject;

    public Question() {
    }

    public Question(Long id, String question, Integer totalMarks, List<QuestionOption> questionOptions, QuestionOption CorrectAnswer, Subject subject) {
        this.id = id;
        this.question = question;
        this.totalMarks = totalMarks;
        this.questionOptions = questionOptions;
        this.CorrectAnswer = CorrectAnswer;
        this.subject = subject;
    }

    public QuestionOption getCorrectAnswer() {
        return this.CorrectAnswer;
    }

    public void setCorrectAnswer(QuestionOption CorrectAnswer) {
        this.CorrectAnswer = CorrectAnswer;
    }

}
