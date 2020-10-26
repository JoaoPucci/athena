package tech.dtech.athena.customer.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import tech.dtech.athena.customer.form.CustomerForm;

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
    private LocalDate birthdate;

    @Embedded
    private Address address;

    public Customer() {
    }

    public Customer(CustomerForm form) {
        this.cpf = form.getCpf();
        this.rg = form.getRg();
        this.fullName = form.getFullName();
        this.email = form.getEmail();
        this.address = form.getAddress().transform();
        this.phoneNumber = form.getPhoneNumber();
        this.birthdate = form.getBirthdate();
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

}
