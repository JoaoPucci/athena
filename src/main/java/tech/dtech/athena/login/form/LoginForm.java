package tech.dtech.athena.login.form;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import tech.dtech.athena.config.validation.constants.DatabaseConstants;
import tech.dtech.athena.login.model.Account;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("unused")
public class LoginForm {

    @NotBlank
    @Email
    @Length(max = DatabaseConstants.DATABASE_STRING_MAX_LENGTH)
    private String email;

    @NotBlank
    @Length(min = Account.PASSWORD_MIN_LENGTH, max = DatabaseConstants.DATABASE_STRING_MAX_LENGTH)
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
