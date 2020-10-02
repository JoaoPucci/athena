package tech.dtech.athena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.dtech.athena.model.User;
import tech.dtech.athena.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository repository;

    @GetMapping("/user")
    public ResponseEntity<User> getAllUsers(String name) {
    	return ResponseEntity.of(repository.findById(1L));
    }

}
