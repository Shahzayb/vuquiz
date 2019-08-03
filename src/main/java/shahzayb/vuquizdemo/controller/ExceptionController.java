package shahzayb.vuquizdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import shahzayb.vuquizdemo.exception.QuizNotAvailableException;
import shahzayb.vuquizdemo.exception.ResourceNotFoundException;

@ControllerAdvice
@Controller
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(QuizNotAvailableException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle(QuizNotAvailableException e, Model model) {
        model.addAttribute("heading", e.getMessage());
        return "error";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handle(ResourceNotFoundException e, Model model) {
        model.addAttribute("heading", e.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handle(Exception e, Model model) {
        model.addAttribute("heading", "Something went wrong. Try again later.");
        return "error";
    }
}
