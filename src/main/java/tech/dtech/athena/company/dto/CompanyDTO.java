package tech.dtech.athena.company.dto;

import tech.dtech.athena.company.model.Company;
import tech.dtech.athena.customer.dto.AddressDTO;

public class CompanyDTO {

    private long id;
    private String cnpj;
    private String tradeName;
    private String companyName;
    private String email;
    private String phoneNumber;
    private AddressDTO address;

    public CompanyDTO(Company company) {
        this.id = company.getId();
        this.cnpj = company.getCnpj();
        this.tradeName = company.getTradeName();
        this.companyName = company.getCompanyName();
        this.email = company.getEmail();
        this.phoneNumber = company.getPhoneNumber();
        this.address = new AddressDTO(company.getAddress());
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

    public AddressDTO getAddress() {
        return address;
    }

}
