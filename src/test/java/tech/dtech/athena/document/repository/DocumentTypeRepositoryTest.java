package tech.dtech.athena.document.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import tech.dtech.athena.document.model.DocumentType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
    void setUp() {
        DocumentType documentType = new DocumentType();
        documentType.setName("Hunter license");
        entityManager.persist(documentType);
    }

    @Test
    void shouldFindDocumentType_GivenItsName() {
        DocumentType documentType = repository.findByName("Hunter license").orElse(null);

        assertNotNull(documentType);
        assertEquals("Hunter license", documentType.getName());
    }

    @Test
    void shouldNotFindDocumentType_GivenItsName_WhenNonExistant() {
        assertTrue(repository.findByName("Ninja headband").isEmpty());
    }

    @Transactional
    @Test
    void shouldUpdateDocumentType_GivenAnExistantId() {
        String wrongName = "super dorkment";
        String correctName = "super document";

        DocumentType documentType = new DocumentType();
        documentType.setName(wrongName);
        DocumentType savedDocumentType = entityManager.persist(documentType);
        savedDocumentType.setName(correctName);

        repository.save(savedDocumentType);

        assertTrue(repository.findByName(correctName).isPresent());
        assertTrue(repository.findByName(wrongName).isEmpty());
    }

    @Transactional
    @Test
    void shouldDeleteDocumentType_GivenAnId() {
        DocumentType documentType = repository.findAll().iterator().next();
        repository.deleteById(documentType.getId());

        assertFalse(repository.findById(documentType.getId()).isPresent());
    }
}
