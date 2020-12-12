package tech.dtech.athena.company.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import tech.dtech.athena.company.form.CompanyForm;
import tech.dtech.athena.customer.model.Address;

@Entity
public class Company {

    public static final String ENTITY_NAME = "Empresa";
    public static final String FIELD_CNPJ_NAME = "CNPJ";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String cnpj;

    private String tradeName;
    private String companyName;
    private String email;
    private String phoneNumber;

    @Embedded
    private Address address;

    public Company() {
    }

    public Company(CompanyForm form) {
        this.cnpj = form.getCnpj();
        this.tradeName = form.getTradeName();
        this.companyName = form.getCompanyName();
        this.email = form.getEmail();
        this.phoneNumber = form.getPhoneNumber();
        this.address = form.getAddress().transform();
    }

    public long getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getTradeName() {
        return tradeName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

}
