package tech.dtech.athena.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import tech.dtech.athena.model.Account;

@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void setUp() {
        Account account = new Account("Goku", "goku@kamehouse.com", "$2a$10$AzmiQREFLZUnxQKj1ZM.mO1uso0WVOsRcP7kV.MqEVKhZ/bV6vQfu");
        entityManager.persist(account);
    }

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
