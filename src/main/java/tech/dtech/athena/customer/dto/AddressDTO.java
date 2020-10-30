package tech.dtech.athena.customer.dto;

import tech.dtech.athena.customer.model.Address;

public class AddressDTO {

    private String zipCode;
    private String addressLine;
    private String addressLine2;
    private String neighbourhood;
    private String city;
    private String state;
    private String number;

    public AddressDTO(Address address) {
        this.zipCode = address.getZipCode();
        this.addressLine = address.getAddressLine();
        this.addressLine2 = address.getAddressLine2();
        this.neighbourhood = address.getNeighbourhood();
        this.city = address.getCity();
        this.state = address.getState();
        this.number = address.getNumber();
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
