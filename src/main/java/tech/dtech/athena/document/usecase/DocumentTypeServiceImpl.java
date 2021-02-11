package tech.dtech.athena.document.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.dtech.athena.config.validation.exceptions.ResourceNotFoundException;
import tech.dtech.athena.customer.DuplicatedRecordException;
import tech.dtech.athena.document.model.DocumentType;
import tech.dtech.athena.document.repository.DocumentTypeRepository;

import java.util.List;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeRepository repository;

    public DocumentTypeServiceImpl(@Autowired DocumentTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DocumentType> getAll() {
        return (List<DocumentType>) repository.findAll();
    }

    @Override
    public DocumentType get(long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException(DocumentType.ENTITY_NAME);
        });
    }

    @Override
    public DocumentType createNew(DocumentType documentType) {
        if (repository.findByName(documentType.getName()).isPresent()) {
            throw new DuplicatedRecordException(DocumentType.ENTITY_NAME, DocumentType.FIELD_NAME_NAME);
        }

        return repository.save(documentType);
    }

    @Override
    public DocumentType update(long id, DocumentType documentType) {
        documentType.setId(id);

        if (repository.findById(id).isPresent()) {
            return repository.save(documentType);
        } else {
            throw new ResourceNotFoundException(DocumentType.ENTITY_NAME);
        }
    }

    @Override
    public void delete(long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(DocumentType.ENTITY_NAME);
        }
    }
}
