package tech.dtech.athena.login.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

    @NotEmpty
    @Email
    @Length(max = 255)
    private String email;

    @NotEmpty
    @Length(min = 8, max = 255)
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UsernamePasswordAuthenticationToken toSecurityCredentials() {
        return new UsernamePasswordAuthenticationToken(getEmail(), getPassword());
    }

}
