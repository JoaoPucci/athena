package tech.dtech.athena.config.validation.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    private String entity;

    public ResourceNotFoundException(String entity) {
        this.entity = entity;
    }

    @Override
    public String getMessage() {
        return entity + " não encontrado";
    }

}
