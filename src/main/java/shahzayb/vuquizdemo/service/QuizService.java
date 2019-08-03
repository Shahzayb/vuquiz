package shahzayb.vuquizdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import shahzayb.vuquizdemo.config.QuizConfig;
import shahzayb.vuquizdemo.entity.Question;
import shahzayb.vuquizdemo.entity.Quiz;
import shahzayb.vuquizdemo.entity.Subject;
import shahzayb.vuquizdemo.entity.User;
import shahzayb.vuquizdemo.exception.ResourceNotFoundException;
import shahzayb.vuquizdemo.repository.QuestionRepository;
import shahzayb.vuquizdemo.repository.QuizRepository;
import shahzayb.vuquizdemo.repository.SubjectRepository;

import java.util.List;

@Service
public class QuizService {

    private SubjectRepository subjectRepository;
    private QuizRepository quizRepository;
    private QuestionRepository questionRepository;

    private QuizConfig quizConfig;

    @Autowired
    public QuizService(SubjectRepository subjectRepository, QuizRepository quizRepository,
                       QuestionRepository questionRepository, QuizConfig quizConfig) {
        this.subjectRepository = subjectRepository;
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.quizConfig = quizConfig;
    }

    public Quiz getQuiz(String subjectCode) {
        Subject subject = subjectRepository.getSubjectBySubjectCode(subjectCode);

        if (subject == null) {
            throw new ResourceNotFoundException("Following resource is not available at the moment");
        }

        List<Question> questions = questionRepository.findRandomQuestionsBySubjectAndSize(
                subject.getId(), quizConfig.getTotalQuestions());

        if (questions.size() < quizConfig.getTotalQuestions()) {
            throw new ResourceNotFoundException("Following resource is not available at the moment");
        }

        Quiz quiz = new Quiz();

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        quiz.setUser(user);
        quiz.setSubject(subject);
        quiz.setQuestions(questions);

        return quiz;
    }

    public void saveQuiz(Quiz quiz) {
        quizRepository.save(quiz);
    }

    public List<Quiz> getQuizzesByUser(User user) {
        return quizRepository.getByUser(user, Sort.by("instant").descending());
    }
}
