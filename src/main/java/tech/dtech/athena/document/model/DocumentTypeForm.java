package tech.dtech.athena.document.model;

import javax.validation.constraints.NotBlank;

@SuppressWarnings("unused")
public class DocumentTypeForm {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public DocumentType transform() {
        return new DocumentType(this);
    }
}
