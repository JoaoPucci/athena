package tech.dtech.athena.customer.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import tech.dtech.athena.config.validation.constants.DatabaseConstants;
import tech.dtech.athena.config.validation.validators.annotations.NullOrNotBlank;
import tech.dtech.athena.customer.model.Address;

public class AddressForm {

    @NotEmpty
    @Length(min = Address.ZIP_CODE_LENGTH, max = Address.ZIP_CODE_LENGTH)
    private String zipCode;

    @NotEmpty
    @Length(max = DatabaseConstants.DATABASE_STRING_MAX_LENGTH)
    private String addressLine;

    @NullOrNotBlank
    @Length(max = DatabaseConstants.DATABASE_STRING_MAX_LENGTH)
    private String addressLine2;

    @NullOrNotBlank
    @Length(max = DatabaseConstants.DATABASE_STRING_MAX_LENGTH)
    private String neighbourhood;

    @NullOrNotBlank
    @Length(max = DatabaseConstants.DATABASE_STRING_MAX_LENGTH)
    private String city;

    @NullOrNotBlank
    @Length(max = DatabaseConstants.DATABASE_STRING_MAX_LENGTH)
    private String state;

    public String getZipCode() {
        return zipCode;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Address transform() {
        return new Address(this);
    }
}
