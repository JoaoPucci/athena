package tech.dtech.athena.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tech.dtech.athena.model.User;

@Service
public class TokenService {
	
	@Value("${athena.jwt.expiration_millis}")
	private String expirationMillis;
	
	@Value("${athena.jwt.secret}")
	private String secret;

	public String generate(Authentication authentication) {
		User loggedUser = (User) authentication.getPrincipal();
		
		Date now = new Date();
		
		return Jwts.builder()
			.setIssuer("DTech Athena API")
			.setSubject(loggedUser.getId().toString())
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + Long.parseLong(expirationMillis)))
			.signWith(SignatureAlgorithm.HS256, secret)
			.compact();
	}

	public boolean isValid(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
		} catch (Exception e) {
			return false;
		}
		
		
		return true;
	}

	public Long getUserId(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
	
}
