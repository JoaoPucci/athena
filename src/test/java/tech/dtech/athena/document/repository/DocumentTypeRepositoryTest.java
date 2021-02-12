package tech.dtech.athena.document.repository;

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

    @Test
    void shouldFindDocumentType_GivenItsName() {
        DocumentType documentType = new DocumentType();
        documentType.setName("Hunter license");
        entityManager.persist(documentType);

        DocumentType foundDocumentType = repository.findByName(documentType.getName()).orElse(null);

        assertNotNull(documentType);
        assertEquals(documentType, foundDocumentType);
    }

    @Test
    void shouldFindDocumentType_GivenItsId() {
        DocumentType newDocumentType = new DocumentType();
        newDocumentType.setName("Super document type");
        DocumentType savedDocumentType = entityManager.persist(newDocumentType);

        DocumentType documentType = repository.findById(savedDocumentType.getId()).orElse(null);

        assertNotNull(documentType);
        assertEquals(newDocumentType, documentType);
    }

    @Test
    void shouldReturnNull_WhenSearchByNonExistentId() {
        long someId = (long) (Math.random() + 999);
        assertTrue(repository.findById(someId).isEmpty());
    }

    @Test
    void shouldNotFindDocumentType_GivenItsName_WhenNonExistant() {
        DocumentType newDocumentType = new DocumentType();
        newDocumentType.setName("Super document type");
        entityManager.persist(newDocumentType);

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
        DocumentType newDocumentType = new DocumentType();
        newDocumentType.setName("Super document type");
        DocumentType savedDocumentType = entityManager.persist(newDocumentType);

        repository.deleteById(savedDocumentType.getId());
        assertFalse(repository.findById(newDocumentType.getId()).isPresent());
    }
}
