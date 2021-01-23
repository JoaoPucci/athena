package tech.dtech.athena.document.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.dtech.athena.customer.DuplicatedRecordException;
import tech.dtech.athena.document.model.DocumentType;
import tech.dtech.athena.document.model.DocumentTypeForm;
import tech.dtech.athena.document.repository.DocumentTypeRepository;

@Service
public class CreateDocumentTypeUseCaseImpl implements CreateDocumentTypeUseCase {
    
    @Autowired
    private DocumentTypeRepository repository;

    @Override
    public DocumentType createFrom(DocumentTypeForm form) {
        if (repository.findByName(form.getName()).isPresent()) {
            throw new DuplicatedRecordException(DocumentType.ENTITY_NAME, DocumentType.FIELD_CPF_NAME);
        }

        return repository.save(form.transform());
    }

}
