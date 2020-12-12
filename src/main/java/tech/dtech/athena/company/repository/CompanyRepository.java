package tech.dtech.athena.company.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import tech.dtech.athena.company.model.Company;

public interface CompanyRepository extends CrudRepository<Company, Long> {

    public Optional<Company> findByCnpj(String cnpj);

}
