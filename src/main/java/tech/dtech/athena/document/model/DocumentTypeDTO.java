package tech.dtech.athena.document.model;

import java.util.List;
import java.util.stream.Collectors;

public class DocumentTypeDTO {

    private long id;
    private String name;

    public static List<DocumentTypeDTO> from(List<DocumentType> documentTypes) {
        return documentTypes.stream().map(DocumentTypeDTO::new).collect(Collectors.toList());
    }

    public DocumentTypeDTO(DocumentType documentType) {
        this.id = documentType.getId();
        this.name = documentType.getName();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
