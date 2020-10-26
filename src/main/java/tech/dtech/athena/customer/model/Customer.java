package tech.dtech.athena.customer.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

    public static final int RG_MIN_LENGTH = 8;
    public static final int RG_MAX_LENGTH = 9;
    public static final String ENTITY_NAME = "Cliente";
    public static final String FIELD_CPF_NAME = "CPF";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String cpf;

    private String rg;
    private String fullName;
    private String email;
    private String phoneNumber;

    @Embedded
    private Address address;

    public Customer() {
    }

    public Customer(String cpf, String rg, String fullName, String email, Address address, String phoneNumber) {
        this.cpf = cpf;
        this.rg = rg;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
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

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
