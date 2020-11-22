package tech.dtech.athena.company.form;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import tech.dtech.athena.company.model.Company;
import tech.dtech.athena.config.validation.constants.DatabaseConstants;
import tech.dtech.athena.config.validation.validators.annotations.E164;
import tech.dtech.athena.config.validation.validators.annotations.NullOrNotBlank;
import tech.dtech.athena.customer.form.AddressForm;

public class CompanyForm {

    @NotEmpty
    @CNPJ
    private String cnpj;

    @NotEmpty
    @Length(max = DatabaseConstants.DATABASE_STRING_MAX_LENGTH)
    private String tradeName;

    @NotEmpty
    @Length(max = DatabaseConstants.DATABASE_STRING_MAX_LENGTH)
    private String companyName;

    @NullOrNotBlank
    @Email
    @Length(max = DatabaseConstants.DATABASE_STRING_MAX_LENGTH)
    private String email;

    @E164
    private String phoneNumber;

    @Valid
    private AddressForm address;

    public String getCnpj() {
        return cnpj.replace(".", "").replace("-", "");
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

    public AddressForm getAddress() {
        return address;
    }

    public Company transform() {
        return new Company(this);
    }
}
