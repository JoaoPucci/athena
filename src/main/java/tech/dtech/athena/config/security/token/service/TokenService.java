package tech.dtech.athena.config.security.token.service;

import org.springframework.security.core.Authentication;

public interface TokenService {

    public String generate(Authentication authentication);

    public boolean isValid(String token);

    public Long getAccountId(String token);

}
