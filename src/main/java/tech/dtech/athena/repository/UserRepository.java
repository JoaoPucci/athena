package tech.dtech.athena.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import tech.dtech.athena.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	Optional<User> findByEmail(String email);
	
}
