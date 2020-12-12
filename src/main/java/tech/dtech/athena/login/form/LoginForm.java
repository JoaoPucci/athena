package tech.dtech.athena.login.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import tech.dtech.athena.config.validation.constants.DatabaseConstants;
import tech.dtech.athena.login.model.Account;

public class LoginForm {

    @NotEmpty
    @Email
    @Length(max = DatabaseConstants.DATABASE_STRING_MAX_LENGTH)
    private String email;

    @NotEmpty
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
