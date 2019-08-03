package shahzayb.vuquizdemo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String subjectCode;

    public Subject() {
    }

    public Subject(Long id, String title, String subjectCode) {
        this.id = id;
        this.title = title;
        this.subjectCode = subjectCode;
    }

}
