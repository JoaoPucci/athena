package tech.dtech.athena.customer.usecase;

import tech.dtech.athena.customer.form.CustomerForm;
import tech.dtech.athena.customer.model.Customer;

public interface CustomerUseCase {

    public Customer createNewCustomer(CustomerForm form);

}
