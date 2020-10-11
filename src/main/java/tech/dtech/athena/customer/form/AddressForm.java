package tech.dtech.athena.customer.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import tech.dtech.athena.customer.model.Address;

public class AddressForm {

    @NotEmpty
    @Length(max = 255)
    private String zipCode;

    @NotEmpty
    @Length(max = 255)
    private String address;

    @NotEmpty
    @Length(max = 255)
    private String neighbourhood;

    @NotEmpty
    @Length(max = 255)
    private String city;

    @NotEmpty
    @Length(max = 255)
    private String state;

    public String getZipCode() {
        return zipCode;
    }

    public String getAddress() {
        return address;
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
        return new Address(zipCode, address, neighbourhood, city, state);
    }
}
