package nl.codebase.entities.user.company;

import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private CompanyDao companyDao;

    public CompanyService(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public void create(CompanyForm companyForm) {
        companyDao.insert(companyForm);
    }
}
