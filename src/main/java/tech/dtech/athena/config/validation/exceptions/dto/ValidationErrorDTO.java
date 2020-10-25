package tech.dtech.athena.config.validation.exceptions.dto;

public class ValidationErrorDTO extends ErrorDTO {

    private String field;

    public ValidationErrorDTO(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }

}
