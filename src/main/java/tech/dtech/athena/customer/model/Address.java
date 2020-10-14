package tech.dtech.athena.customer.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    public static final int ZIP_CODE_LENGTH = 8;

    private String zipCode;
    private String address;
    private String neighbourhood;
    private String city;
    private String state;

    public Address() {
    }

    public Address(String zipCode, String address, String neighbourhood, String city, String state) {
        this.zipCode = zipCode;
        this.address = address;
        this.neighbourhood = neighbourhood;
        this.city = city;
        this.state = state;
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
