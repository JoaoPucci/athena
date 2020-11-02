package tech.dtech.athena.config.security.token.usecase;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import tech.dtech.athena.config.security.token.service.TokenService;
import tech.dtech.athena.login.model.Account;
import tech.dtech.athena.repository.AccountRepository;

@Component
public class TokenAuthenticationUseCaseImpl implements TokenAuthenticationUseCase {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void authenticateWith(String token) {
        if (tokenService.isValid(token)) {
            authenticate(token);
        }
    }

    private void authenticate(String token) {
        Long accountId = tokenService.getAccountId(token);
        Account account = accountRepository.findById(accountId).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(account, null,
                account.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public String getTokenFrom(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
            return null;
        }

        return token.substring(7, token.length());
    }

}
