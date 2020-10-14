package tech.dtech.athena.customer.dto;

import tech.dtech.athena.customer.model.Address;

public class AddressDTO {

    private String zipCode;
    private String address;
    private String neighbourhood;
    private String city;
    private String state;

    public AddressDTO(Address address) {
        this.zipCode = address.getZipCode();
        this.address = address.getAddress();
        this.neighbourhood = address.getNeighbourhood();
        this.city = address.getCity();
        this.state = address.getState();
    }

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

}
