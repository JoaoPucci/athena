package tech.dtech.athena.customer;

public class DuplicatedRecordException extends RuntimeException{

    private String field;
    private String entity;

    public DuplicatedRecordException(String entity, String field) {
        this.entity = entity;
        this.field = field;
    }

    @Override
    public String getMessage() {
        return "Já existe um registro de " + entity + " com o mesmo " + field;
    }

}
