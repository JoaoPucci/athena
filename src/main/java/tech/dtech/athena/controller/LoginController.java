package tech.dtech.athena.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.dtech.athena.config.security.TokenService;
import tech.dtech.athena.controller.form.LoginForm;
import tech.dtech.athena.model.TokenDTO;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> authenticate(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken credentials = form.toSecurityCredentials();
		
		try {
			Authentication authentication = authenticationManager.authenticate(credentials);
			String token = tokenService.generate(authentication);		
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
