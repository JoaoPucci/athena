package tech.dtech.athena.customer.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import tech.dtech.athena.customer.model.Customer;

public class CustomerForm {

    @NotEmpty
    @CPF
    @Length(max = 255)
    private String cpf;

    @Length(min = 9)
    private String rg;

    @NotEmpty
    @Length(max = 255)
    private String fullName;

    @NotEmpty
    @Email
    @Length(max = 255)
    private String email;

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

    public Customer transform() {
        return new Customer(cpf, rg, fullName, email, address.transform());
    }
}
