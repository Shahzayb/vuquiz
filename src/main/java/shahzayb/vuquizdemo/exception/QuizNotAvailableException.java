package shahzayb.vuquizdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QuizNotAvailableException extends RuntimeException {
    public QuizNotAvailableException(String message) {
        super(message);
    }
}
