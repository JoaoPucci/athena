package tech.dtech.athena.customer.form;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import tech.dtech.athena.config.validation.constants.DatabaseConstants;
import tech.dtech.athena.config.validation.validators.annotations.E164;
import tech.dtech.athena.config.validation.validators.annotations.NullOrNotBlank;
import tech.dtech.athena.customer.model.Customer;

public class CustomerForm {

    @NotEmpty
    @CPF
    private String cpf;

    @Length(min = Customer.RG_MIN_LENGTH, max = Customer.RG_MAX_LENGTH)
    private String rg;

    @NotEmpty
    @Length(max = DatabaseConstants.DATABASE_STRING_MAX_LENGTH)
    private String fullName;

    @NullOrNotBlank
    @Email
    @Length(max = DatabaseConstants.DATABASE_STRING_MAX_LENGTH)
    private String email;

    @E164
    private String phoneNumber;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    private AddressForm address;

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

    public AddressForm getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Customer transform() {
        return new Customer(this);
    }
}
