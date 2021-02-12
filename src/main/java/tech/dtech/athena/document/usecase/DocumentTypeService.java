package tech.dtech.athena.document.usecase;

import tech.dtech.athena.document.model.DocumentType;

import java.util.List;

public interface DocumentTypeService {

    List<DocumentType> getAll();

    DocumentType get(long id);

    DocumentType createNew(DocumentType documentType);

    DocumentType update(long id, DocumentType documentType);

    void delete(long id);
}
