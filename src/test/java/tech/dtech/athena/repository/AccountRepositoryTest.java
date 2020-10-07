package tech.dtech.athena.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import tech.dtech.athena.model.Account;

@DataJpaTest
@ActiveProfiles(value = "test")
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @Test
    public void shouldFindAccount_GivenItsEmail() {
        Account account = repository.findByEmail("goku@kamehouse.com").get();
        assertEquals("Goku", account.getName());
    }

    @Test
    public void shouldNotFindAccount_GivenItsEmail_WhenNotExistant() {
        assertTrue(repository.findByEmail("goku@capsulecorp.com").isEmpty());
    }

}
