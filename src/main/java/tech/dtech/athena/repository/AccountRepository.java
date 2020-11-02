package tech.dtech.athena.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import tech.dtech.athena.login.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByEmail(String email);

}
