package nl.codebase.entities.account.account;

import nl.codebase.entities.common.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private AccountDao accountDao;

    @Autowired
    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Optional<Account> findByEmail(String email) {
        Optional<PersistedAccount> persistedAccountOptional = accountDao.findByEmail(email);
        return persistedAccountOptional.map(PersistedAccount::getAccount);
    }
}
