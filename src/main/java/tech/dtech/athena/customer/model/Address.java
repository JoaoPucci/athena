package tech.dtech.athena.customer.model;

import javax.persistence.Embeddable;

import tech.dtech.athena.customer.form.AddressForm;

@Embeddable
public class Address {

    public static final int ZIP_CODE_LENGTH = 8;

    private String zipCode;
    private String addressLine;
    private String addressLine2;
    private String neighbourhood;
    private String city;
    private String state;
    private String number;

    public Address() {
    }

    public Address(AddressForm form) {
        this.zipCode = form.getZipCode();
        this.addressLine = form.getAddressLine();
        this.addressLine2 = form.getAddressLine2();
        this.neighbourhood = form.getNeighbourhood();
        this.city = form.getCity();
        this.state = form.getState();
        this.number = form.getNumber();
    }

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

    public String getNumber() {
        return number;
    }

}
