package shahzayb.vuquizdemo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class QuestionOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionOption;

    @ManyToOne
    @JoinColumn(name = "question")
    private Question question;

}
