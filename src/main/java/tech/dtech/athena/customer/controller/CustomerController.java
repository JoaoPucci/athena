package tech.dtech.athena.customer.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import tech.dtech.athena.customer.dto.CustomerDTO;
import tech.dtech.athena.customer.form.CustomerForm;
import tech.dtech.athena.customer.model.Customer;
import tech.dtech.athena.customer.repository.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @PostMapping
    public ResponseEntity<CustomerDTO> create(@RequestBody @Valid CustomerForm customerForm, UriComponentsBuilder uriBuilder){
        boolean isDuplicated = repository.findByCpf(customerForm.getCpf()).isPresent();

        if (isDuplicated) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            Customer customer = repository.save(customerForm.transform());
            URI uri = uriBuilder.path("customers/{id}").buildAndExpand(customer.getId()).toUri();
            return ResponseEntity.created(uri).body(new CustomerDTO(customer));
        }
    }
}
