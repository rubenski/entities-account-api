package nl.codebase.entities.account.account;

import lombok.Getter;
import lombok.Setter;
import nl.codebase.entities.common.account.Account;

@Getter
@Setter
public class PersistedAccount {

    private Account account = new Account();
    private String password;
    private String salt;
}
