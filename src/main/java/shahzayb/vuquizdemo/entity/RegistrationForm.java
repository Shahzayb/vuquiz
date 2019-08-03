package shahzayb.vuquizdemo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import shahzayb.vuquizdemo.entity.validator.NotTaken;
import shahzayb.vuquizdemo.entity.validator.PasswordMatches;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@PasswordMatches
public class RegistrationForm {

    @NotBlank(message = "username is required")
    @NotTaken
    private String username;

    @NotBlank(message = "password is required")
    @Size(min = 8, max = 30, message = "password length must be between 8 and 30")
    private String password;

    private String confirmPassword;


    @NotBlank(message = "first name is required")
    private String firstName;

    @NotBlank(message = "last name is required")
    private String lastName;

    public RegistrationForm() {
    }

    public RegistrationForm(@NotBlank(message = "username is required") String username, @NotBlank(message = "password is required") @Size(min = 8, max = 30, message = "password length must be between 8 and 30") String password, String confirmPassword, @NotBlank(message = "first name is required") String firstName, @NotBlank(message = "last name is required") String lastName) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User toUser(PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }

}
