package tech.dtech.athena.document.usecase;

import tech.dtech.athena.document.model.DocumentType;
import tech.dtech.athena.document.model.DocumentTypeForm;

import java.util.List;

public interface DocumentTypeService {

    List<DocumentType> getAll();

    DocumentType createNew(DocumentTypeForm form);
}
