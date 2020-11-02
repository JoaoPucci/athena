package tech.dtech.athena.config.security.token.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tech.dtech.athena.login.model.Account;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${athena.jwt.expiration_millis}")
    private String expirationMillis;

    @Value("${athena.jwt.secret}")
    private String secret;

    @Override
    public String generate(Authentication authentication) {
        Account loggedUser = (Account) authentication.getPrincipal();

        Date now = new Date();

        return Jwts.builder().setIssuer("DTech Athena API").setSubject(loggedUser.getId().toString()).setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Long.parseLong(expirationMillis)))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    @Override
    public boolean isValid(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public Long getAccountId(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

}
