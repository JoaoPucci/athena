package tech.dtech.athena.config.security.token.usecase;

import javax.servlet.http.HttpServletRequest;

public interface TokenAuthenticationUseCase {

    public String getTokenFrom(HttpServletRequest request);

    public void authenticateWith(String token);

}
