package nl.codebase.entities.user.signup;

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
        int insertId = companyDao.insert(signUpForm.getCompany());
        accountDao.insert(signUpForm.getAccount(), insertId);
    }

    public Optional<Account> findAccountByEmail(String email) {
        return accountDao.findByEmail(email);
    }

}
