package tech.dtech.athena.customer.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.dtech.athena.customer.DuplicatedRecordException;
import tech.dtech.athena.customer.form.CustomerForm;
import tech.dtech.athena.customer.model.Customer;
import tech.dtech.athena.customer.repository.CustomerRepository;

@Service
public class CustomerUseCaseImpl implements CustomerUseCase {

    @Autowired
    private CustomerRepository repository;

    @Override
    public Customer createNewCustomer(CustomerForm form) {
        if (repository.findByCpf(form.getCpf()).isPresent()) {
            throw new DuplicatedRecordException(Customer.ENTITY_NAME, Customer.FIELD_CPF_NAME);
        }

        return repository.save(form.transform());
    }

}
