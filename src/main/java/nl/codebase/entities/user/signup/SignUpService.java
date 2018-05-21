package nl.codebase.entities.user.signup;

import nl.codebase.entities.common.FaceterConstants;
import nl.codebase.entities.common.account.Grants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SignUpService {

    private CompanyDao companyDao;
    private AccountDao accountDao;

    @Autowired
    public SignUpService(CompanyDao companyDao, AccountDao accountDao) {
        this.companyDao = companyDao;
        this.accountDao = accountDao;
    }

    public SignUpService(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Transactional
    public void signUp(SignUpForm signUpForm) {
        int companyId = companyDao.insert(signUpForm.getCompany());
        signUpForm.getAccount().setGrants(Grants.from(FaceterConstants.GRANT.COMPANY_USER));
        accountDao.insert(signUpForm.getAccount(), companyId);
    }

    public Optional<Account> findAccountByEmail(String email) {
        return accountDao.findByEmail(email);
    }

}
