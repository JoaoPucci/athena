package tech.dtech.athena.company.usecase;

import tech.dtech.athena.company.form.CompanyForm;
import tech.dtech.athena.company.model.Company;

public interface CompanyUseCase {

    public Company createNewCompany(CompanyForm form);

}
