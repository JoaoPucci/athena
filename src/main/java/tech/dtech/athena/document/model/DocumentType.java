package tech.dtech.athena.document.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DocumentType {

    public static final String ENTITY_NAME = "Tipo de documento";
    public static final String FIELD_CPF_NAME = "Nome";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public DocumentType() {
    }

    public DocumentType(DocumentTypeForm form) {
        this.name = form.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
