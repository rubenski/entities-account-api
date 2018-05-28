package nl.codebase.entities.account;

import nl.codebase.entities.common.EncryptionUtil;
import nl.codebase.entities.common.account.Account;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AccountService
{

    private AccountDao accountDao;

    @Autowired
    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Optional<Account> authenticate(String email, String password) {
        if(StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("Email and password cannot be empty");
        }
        Optional<Account> accountOptional = accountDao.findByEmail(email);
        if(!accountOptional.isPresent()) {
            return Optional.empty();
        }

        Account account = accountOptional.get();

        String suppliedPasswordHash = EncryptionUtil.generatePasswordHash(account.getSalt(), password);


        if(!suppliedPasswordHash.equals(account.getPassword())) {
            return Optional.empty();
        }

        return Optional.of(account);
    }

    public Optional<Account> findByEmail(String email) {
        return accountDao.findByEmail(email);
    }
}
