package tech.dtech.athena.config.security.token.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import tech.dtech.athena.config.security.token.usecase.TokenAuthenticationUseCase;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private TokenAuthenticationUseCase useCase;

    public TokenAuthenticationFilter(TokenAuthenticationUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = useCase.getTokenFrom(request);

        useCase.authenticateWith(token);

        filterChain.doFilter(request, response);
    }

}
