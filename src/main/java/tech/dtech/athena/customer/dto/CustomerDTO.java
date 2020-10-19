package tech.dtech.athena.customer.dto;

import tech.dtech.athena.customer.model.Customer;

public class CustomerDTO {

    private long id;
    private String cpf;
    private String rg;
    private String fullName;
    private String email;
    private AddressDTO address;
    private String phoneNumber;

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.cpf = customer.getCpf();
        this.rg = customer.getRg();
        this.fullName = customer.getFullName();
        this.email = customer.getEmail();
        this.address = new AddressDTO(customer.getAddress());
        this.phoneNumber = customer.getPhoneNumber();
    }

    public long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
