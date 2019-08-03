package shahzayb.vuquizdemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;

@Data
@Configuration
@ConfigurationProperties(prefix = "vu.quiz")
@Validated
public class QuizConfig {

    @Min(value = 1, message = "quiz should have at least 1 question")
    private int totalQuestions = 10;
    @Min(value = 10, message = "question time should be at least 10 seconds")
    private int secondsPerQuestion = 90;

}
