package tech.dtech.athena.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import tech.dtech.athena.model.Account;

@RunWith(SpringRunner.class)
@DataJpaTest
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
