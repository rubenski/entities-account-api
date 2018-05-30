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

    // TODO: split of fetching of the actual account and authentication.
    public Optional<Account> authenticate(String email, String suppliedPassword) {
        if(StringUtils.isBlank(email) || StringUtils.isBlank(suppliedPassword)) {
            throw new IllegalArgumentException("Email and suppliedPassword cannot be empty");
        }
        Optional<Account> accountOptional = accountDao.findByEmail(email);
        if(!accountOptional.isPresent()) {
            return Optional.empty();
        }

        Account account = accountOptional.get();

        String suppliedPasswordHash = EncryptionUtil.generatePasswordHash(account.getSalt(), suppliedPassword);

        if(!suppliedPasswordHash.equals(account.getPassword())) {
            return Optional.empty();
        }

        account.clearPasswords();

        return Optional.of(account);
    }

    public Optional<Account> findByEmail(String email) {
        return accountDao.findByEmail(email);
    }
}
