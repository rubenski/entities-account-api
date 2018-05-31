package nl.codebase.entities.account.account;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/exists", method = RequestMethod.POST)
    public boolean accountExists(@Valid @RequestBody AccountController.AccountExists accountExists) {
        return accountService.findByEmail(accountExists.getEmail()).isPresent();
    }

    @Getter
    @Setter
    private static class AccountExists {
        @Email
        private String email;
    }
}
