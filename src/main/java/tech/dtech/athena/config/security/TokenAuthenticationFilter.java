package tech.dtech.athena.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import tech.dtech.athena.model.Account;
import tech.dtech.athena.repository.AccountRepository;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private AccountRepository accountRepository;

    public TokenAuthenticationFilter(TokenService tokenService, AccountRepository accountRepository) {
        this.tokenService = tokenService;
        this.accountRepository = accountRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = getToken(request);
        boolean isValid = tokenService.isValid(token);

        if (isValid) {
            authenticate(token);
        }

        filterChain.doFilter(request, response);
    }

    private void authenticate(String token) {
        Long accountId = tokenService.getAccountId(token);
        Account account = accountRepository.findById(accountId).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(account, null,
                account.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
            return null;
        }

        return token.substring(7, token.length());
    }

}
