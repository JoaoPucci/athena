package tech.dtech.athena.document.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tech.dtech.athena.document.model.DocumentType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class DocumentTypeRepositoryTest {

    @Autowired
    private DocumentTypeRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void setUp() {
        DocumentType documentType = new DocumentType();
        documentType.setName("Hunter license");
        entityManager.persist(documentType);
    }

    @Test
    public void shouldFindDocumentType_GivenItsName() {
        DocumentType documentType = repository.findByName("Hunter license").orElse(null);

        assertNotNull(documentType);
        assertEquals("Hunter license", documentType.getName());
    }

    @Test
    public void shouldNotFindDocumentType_GivenItsName_WhenNonExistant() {
        assertTrue(repository.findByName("Ninja headband").isEmpty());
    }
}
