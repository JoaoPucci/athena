package tech.dtech.athena.document.usecase;

import tech.dtech.athena.document.model.DocumentType;

import java.util.List;

public interface DocumentTypeService {

    List<DocumentType> getAll();

    DocumentType createNew(DocumentType documentType);
}
