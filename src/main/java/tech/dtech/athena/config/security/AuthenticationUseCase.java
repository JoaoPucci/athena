package tech.dtech.athena.config.security;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationUseCase {

    public String getTokenFrom(HttpServletRequest request);

    public void authenticateWith(String token);

}
