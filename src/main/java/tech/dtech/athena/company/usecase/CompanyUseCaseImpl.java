package tech.dtech.athena.company.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.dtech.athena.company.form.CompanyForm;
import tech.dtech.athena.company.model.Company;
import tech.dtech.athena.company.repository.CompanyRepository;
import tech.dtech.athena.customer.DuplicatedRecordException;

@Service
public class CompanyUseCaseImpl implements CompanyUseCase {

    @Autowired
    private CompanyRepository repository;

    @Override
    public Company createNewCompany(CompanyForm form) {
        if (repository.findByCnpj(form.getCnpj()).isPresent()) {
            throw new DuplicatedRecordException(Company.ENTITY_NAME, Company.FIELD_CNPJ_NAME);
        }

        return repository.save(form.transform());
    }

}
