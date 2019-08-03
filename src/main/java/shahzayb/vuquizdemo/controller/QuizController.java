package shahzayb.vuquizdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shahzayb.vuquizdemo.config.QuizConfig;
import shahzayb.vuquizdemo.entity.Quiz;
import shahzayb.vuquizdemo.entity.User;
import shahzayb.vuquizdemo.exception.QuizNotAvailableException;
import shahzayb.vuquizdemo.service.QuizService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Instant;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    private QuizService quizService;
    private QuizConfig quizConfig;

    @Autowired
    public QuizController(QuizService quizService, QuizConfig quizConfig) {
        this.quizService = quizService;
        this.quizConfig = quizConfig;
    }

    @GetMapping("/start/{subjectCode}")
    public String startQuiz(@PathVariable String subjectCode, HttpSession session) {
        Quiz quiz = quizService.getQuiz(subjectCode);

        session.setAttribute("quiz", quiz);
        session.removeAttribute("questionTimestamp");
        session.setAttribute("secPerQuestion", quizConfig.getSecondsPerQuestion());

        return "redirect:/quiz/question";
    }

    @GetMapping("/question")
    public String getCurrentQuestion(@SessionAttribute(name = "quiz", required = false) Quiz quiz, HttpSession httpSession) {
        if (quiz == null) {
            throw new QuizNotAvailableException("There is no quiz in progress right now");
        }

        Instant instant = (Instant) httpSession.getAttribute("questionTimestamp");
        if (instant == null) {
            instant = Instant.now();
            httpSession.setAttribute("questionTimestamp", instant);
        }

        return "quiz";
    }

    @PostMapping("/question")
    public String saveAndNext(@SessionAttribute(name = "quiz", required = false) Quiz quiz, HttpSession httpSession, HttpServletRequest request) {
        if (quiz == null) {
            throw new QuizNotAvailableException("There is no quiz in progress right now");
        }

        try {
            Long id = Long.parseLong(request.getParameter("attemptedAnswerId"));
            if (quiz.getCurrentQuestion().getCorrectAnswer().getId().equals(id)) {
                quiz.addResult(quiz.getCurrentQuestion().getTotalMarks());
            }
        } catch (Exception e) { }

        int currentQuestion = quiz.getCurQuestionNumber();
        if (currentQuestion == quizConfig.getTotalQuestions()) {
            quizService.saveQuiz(quiz);
            httpSession.removeAttribute("quiz");
            httpSession.removeAttribute("questionTimestamp");
            httpSession.removeAttribute("secPerQuestion");
            return "redirect:/quiz/result";
        }

        currentQuestion++;
        quiz.setCurQuestionNumber(currentQuestion);
        httpSession.removeAttribute("questionTimestamp");

        return "redirect:/quiz/question";
    }

    @GetMapping("/result")
    public String result(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("quizzes", quizService.getQuizzesByUser(user));
        return "result";
    }
}
