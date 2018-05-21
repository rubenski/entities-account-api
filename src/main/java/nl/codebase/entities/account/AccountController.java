package nl.codebase.entities.account;

import lombok.Getter;
import lombok.Setter;
import nl.codebase.entities.common.account.Account;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // TODO: secure this with mutual TLS: https://stackoverflow.com/questions/33808603/implementing-2-way-ssl-using-spring-boot
    // Currently this is secured by not mapping the path in the Zuul proxy and assuming the app will deployed behind a NAT gateway
    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public ResponseEntity<Account> findAccount(@PathParam("email") String email) {
        Optional<Account> accountOptional = accountService.findByEmail(email);
        if (!accountOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accountOptional.get());
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
