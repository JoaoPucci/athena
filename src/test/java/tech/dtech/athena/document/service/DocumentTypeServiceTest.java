package tech.dtech.athena.document.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.dtech.athena.document.model.DocumentType;
import tech.dtech.athena.document.repository.DocumentTypeRepository;
import tech.dtech.athena.document.usecase.DocumentTypeServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(MockitoExtension.class)
class DocumentTypeServiceTest {

    @Mock
    private DocumentTypeRepository repository;

    @InjectMocks
    private DocumentTypeServiceImpl service;

    @Test
    void shouldCallRepositoryFindAllOnce_WhenServiceGetAllIsCalled_ThenReturnAList() {
        DocumentType documentType = new DocumentType();
        documentType.setId(1L);
        documentType.setName("Hunter license");

        DocumentType documentType2 = new DocumentType();
        documentType.setId(2L);
        documentType.setName("Hero license");

        List<DocumentType> documentTypesFromRepository = Arrays.asList(documentType, documentType2);
        Mockito.when(repository.findAll()).thenReturn(documentTypesFromRepository);

        List<DocumentType> documentTypes = service.getAll();

        Mockito.verify(repository, Mockito.times(1)).findAll();
        assertSame(documentTypesFromRepository, documentTypes);
        assertIterableEquals(documentTypesFromRepository, documentTypes);
    }

    @Test
    void shouldCallRepositorySaveOnce_WhenServiceCreateNewIsCalled() {
        DocumentType originalDocumentType = new DocumentType();
        originalDocumentType.setName("Hunter license");

        DocumentType expectedSavedDocumentType = new DocumentType();
        expectedSavedDocumentType.setId(1L);
        expectedSavedDocumentType.setName("Hunter license");
        Mockito.when(repository.save(originalDocumentType)).thenReturn(expectedSavedDocumentType);

        DocumentType actualSavedDocumentType = service.createNew(originalDocumentType);

        Mockito.verify(repository, Mockito.times(1)).save(originalDocumentType);
        assertSame(expectedSavedDocumentType, actualSavedDocumentType);
        assertEquals(originalDocumentType.getName(), actualSavedDocumentType.getName());
    }
}
