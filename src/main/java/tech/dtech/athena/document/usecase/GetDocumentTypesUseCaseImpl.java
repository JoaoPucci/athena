package tech.dtech.athena.document.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.dtech.athena.document.model.DocumentType;
import tech.dtech.athena.document.repository.DocumentTypeRepository;

@Service
public class GetDocumentTypesUseCaseImpl implements GetDocumentTypesUseCase {

    @Autowired
    private DocumentTypeRepository repository;

    @Override
    public List<DocumentType> execute() {
        return (List<DocumentType>) repository.findAll();
    }

}
