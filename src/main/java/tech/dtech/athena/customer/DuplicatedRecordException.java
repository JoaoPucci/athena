package tech.dtech.athena.customer;

public class DuplicatedRecordException extends RuntimeException{

    private String field;
    private String entity;

    public DuplicatedRecordException(String field, String entity) {
        this.field = field;
        this.entity = entity;
    }

    @Override
    public String getMessage() {
        return "Já existe um registro de " + entity + " com o mesmo " + field;
    }

}
