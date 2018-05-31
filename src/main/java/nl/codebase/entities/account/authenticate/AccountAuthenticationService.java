package nl.codebase.entities.account.authenticate;

import nl.codebase.entities.account.account.AccountDao;
import nl.codebase.entities.account.account.PersistedAccount;
import nl.codebase.entities.common.EncryptionUtil;

import nl.codebase.entities.common.account.Account;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountAuthenticationService
{

    private AccountDao accountDao;

    @Autowired
    public AccountAuthenticationService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


    public Optional<Account> authenticate(String email, String suppliedPassword) {
        if(StringUtils.isBlank(email) || StringUtils.isBlank(suppliedPassword)) {
            throw new IllegalArgumentException("Email and suppliedPassword cannot be empty");
        }
        Optional<PersistedAccount> accountOptional = accountDao.findByEmail(email);
        if(!accountOptional.isPresent()) {
            return Optional.empty();
        }

        PersistedAccount persistedAccount = accountOptional.get();

        String suppliedPasswordHash = EncryptionUtil.generatePasswordHash(persistedAccount.getSalt(), suppliedPassword);

        if(!suppliedPasswordHash.equals(persistedAccount.getPassword())) {
            return Optional.empty();
        }


        return Optional.of(persistedAccount.getAccount());
    }


}
