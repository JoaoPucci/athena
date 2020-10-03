package tech.dtech.athena.login.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

    private String email;
    private String password;

    @NotNull
    @NotEmpty
    @Email
    public String getEmail() {
        return email;
    }

    @NotNull
    @NotEmpty
    @Length(min = 8)
    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken toSecurityCredentials() {
        return new UsernamePasswordAuthenticationToken(getEmail(), getPassword());
    }

}
