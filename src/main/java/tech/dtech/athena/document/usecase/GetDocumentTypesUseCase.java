package tech.dtech.athena.document.usecase;

import java.util.List;

import tech.dtech.athena.document.model.DocumentType;

public interface GetDocumentTypesUseCase {

    public List<DocumentType> execute();

}
