package shahzayb.vuquizdemo.entity.validator;

import org.springframework.beans.factory.annotation.Autowired;
import shahzayb.vuquizdemo.entity.User;
import shahzayb.vuquizdemo.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<NotTaken, String> {

    private UserRepository userRepository;

    @Autowired
    public UsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(NotTaken constraint) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return true;
        }
        return false;
    }
}
