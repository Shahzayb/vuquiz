package shahzayb.vuquizdemo.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import shahzayb.vuquizdemo.entity.Question;
import shahzayb.vuquizdemo.entity.QuestionOption;
import shahzayb.vuquizdemo.entity.Subject;
import shahzayb.vuquizdemo.repository.QuestionRepository;
import shahzayb.vuquizdemo.repository.SubjectRepository;
import shahzayb.vuquizdemo.repository.UserRepository;

import java.util.Arrays;

@Component
@Profile("dev")
public class InitialData implements ApplicationListener<ApplicationReadyEvent> {

    private SubjectRepository subjectRepository;
    private QuestionRepository questionRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public InitialData(SubjectRepository subjectRepository, QuestionRepository questionRepository, UserRepository userRepository) {
        this.subjectRepository = subjectRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.encoder = new BCryptPasswordEncoder();
    }

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        initData();
    }

    public void initData() {

        questionRepository.deleteAll();
        subjectRepository.deleteAll();
        userRepository.deleteAll();

        Subject CS101 = new Subject();
        CS101.setSubjectCode("CS101");
        CS101.setTitle("Introduction to Programming");

        subjectRepository.save(CS101);


        Subject MTH101 = new Subject();
        MTH101.setSubjectCode("MTH101");
        MTH101.setTitle("Calculus and Analytical Geometry");

        subjectRepository.save(MTH101);


        questionRepository.save(createQuestion(
                CS101,
                "Human are better than computers at",
                new String[]{
                        "Efficiency",
                        "Accuracy",
                        "Pattern recognition",
                        "None of the given choices"
                },
                2
        ));

        questionRepository.save(createQuestion(
                CS101,
                "Cray-1 was first commercial _________ computer",
                new String[]{
                        "Super",
                        "Mini",
                        "Micro",
                        "Personal"
                },
                0
        ));

        questionRepository.save(createQuestion(
                CS101,
                "URL is a/an ________",
                new String[]{
                        "Device",
                        "Component",
                        "Address",
                        "Tool"
                },
                2
        ));

        questionRepository.save(createQuestion(
                CS101,
                "Mainframe Computers are also called _____",
                new String[]{
                        "Enterprise Servers",
                        "Personal Servers",
                        "Enterprise Managers",
                        "Window Servers"
                },
                0
        ));

        questionRepository.save(createQuestion(
                CS101,
                "Which of the following is NOT a category of Mobile Computers",
                new String[]{
                        "Laptop",
                        "Palmtop",
                        "Desktop",
                        "Wearable"
                },
                2
        ));

        questionRepository.save(createQuestion(
                CS101,
                "Preliminary exploration of possible solutions, technologies, suppliers is called",
                new String[]{
                        "Viability",
                        "Feasibility",
                        "Specification",
                        "Integration"
                },
                1
        ));

        questionRepository.save(createQuestion(
                CS101,
                "__________ give us the ability to manipulate data through reference instead of actual value",
                new String[]{
                        "Constants",
                        "Variables",
                        "Data Types",
                        "Operators"
                },
                1
        ));

        questionRepository.save(createQuestion(
                CS101,
                "Consider the following statement written in JavaScript:\\nstr = \"Hello\" + \" World\"\\nWhat will be the value of str?",
                new String[]{
                        "HelloWorld",
                        "Hello World",
                        "Hello + World",
                        "It will result in error"
                },
                1
        ));

        questionRepository.save(createQuestion(
                CS101,
                "If a computer could pass the Turing test then it would be able to",
                new String[]{
                        "think like human beings",
                        "do the things faster",
                        "win a million dollar prize",
                        "store more information"
                },
                0
        ));

        questionRepository.save(createQuestion(
                CS101,
                "Communication protocol is a __________that governs the flow of information over a network",
                new String[]{
                        "Set of protocols",
                        "Set of rules",
                        "Device",
                        "Set of methods"
                },
                1
        ));

    }

    private Question createQuestion(Subject subject, String questionText, String[] optionsText, int ansIndex) {
        Question question = new Question();
        question.setQuestion(questionText);
        question.setSubject(subject);
        question.setTotalMarks(1);

        QuestionOption option1 = new QuestionOption();
        option1.setQuestion(question);
        option1.setQuestionOption(optionsText[0]);

        QuestionOption option2 = new QuestionOption();
        option2.setQuestion(question);
        option2.setQuestionOption(optionsText[1]);

        QuestionOption option3 = new QuestionOption();
        option3.setQuestion(question);
        option3.setQuestionOption(optionsText[2]);

        QuestionOption option4 = new QuestionOption();
        option4.setQuestion(question);
        option4.setQuestionOption(optionsText[3]);

        question.setQuestionOptions(Arrays.asList(option1, option2, option3, option4));

        question.setCorrectAnswer(question.getQuestionOptions().get(ansIndex));

        return question;
    }
}