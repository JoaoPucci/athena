package tech.dtech.athena.document.model;

import javax.validation.constraints.NotEmpty;

public class DocumentTypeForm {

    @SuppressWarnings("unused")
    @NotEmpty
    private String name;
    
    public String getName() {
        return name;
    }
    
    public DocumentType transform() {
        return new DocumentType(this);
    }
}
