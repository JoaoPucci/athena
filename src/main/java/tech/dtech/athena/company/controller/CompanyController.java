package tech.dtech.athena.company.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import tech.dtech.athena.company.dto.CompanyDTO;
import tech.dtech.athena.company.form.CompanyForm;
import tech.dtech.athena.company.model.Company;
import tech.dtech.athena.company.usecase.CompanyUseCase;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyUseCase useCase;

    @PostMapping
    public ResponseEntity<CompanyDTO> create(@RequestBody @Valid CompanyForm companyForm,
            UriComponentsBuilder uriBuilder) {
        Company company = useCase.createNewCompany(companyForm);
        URI uri = uriBuilder.path("companies/{id}").buildAndExpand(company.getId()).toUri();

        return ResponseEntity.created(uri).body(new CompanyDTO(company));
    }
}
