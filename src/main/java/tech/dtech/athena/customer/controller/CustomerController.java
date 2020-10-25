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
import tech.dtech.athena.customer.usecase.CustomerUseCase;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerUseCase useCase;

    @PostMapping
    public ResponseEntity<CustomerDTO> create(@RequestBody @Valid CustomerForm customerForm,
            UriComponentsBuilder uriBuilder) {
        Customer customer = useCase.createNewCustomer(customerForm);
        URI uri = uriBuilder.path("customers/{id}").buildAndExpand(customer.getId()).toUri();

        return ResponseEntity.created(uri).body(new CustomerDTO(customer));
    }
}
