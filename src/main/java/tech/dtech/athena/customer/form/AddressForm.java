package tech.dtech.athena.customer.form;

import tech.dtech.athena.customer.model.Address;

public class AddressForm {

    private String zipCode;
    private String address;
    private String neighbourhood;
    private String city;
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
