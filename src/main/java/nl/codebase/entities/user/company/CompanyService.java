package nl.codebase.entities.user.company;

import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private CompanyDao companyDao;

    public CompanyService(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public CompanyForm create(CompanyForm companyForm) {
        String uuid = companyDao.insert(companyForm);
        companyForm.setUuid(uuid);
        return companyForm;
    }
}
