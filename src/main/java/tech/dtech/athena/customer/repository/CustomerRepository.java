package tech.dtech.athena.customer.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import tech.dtech.athena.customer.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    public Optional<Customer> findByCpf(String cpf);

}
