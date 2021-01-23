package tech.dtech.athena.document.usecase;

import tech.dtech.athena.document.model.DocumentType;
import tech.dtech.athena.document.model.DocumentTypeForm;

public interface CreateDocumentTypeUseCase {
    
    DocumentType createFrom(DocumentTypeForm form);
}
