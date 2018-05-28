package nl.codebase.entities.account;

import lombok.Getter;
import lombok.Setter;
import nl.codebase.entities.common.account.Account;
import org.hibernate.validator.constraints.Email;
import org.postgresql.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.Charset;
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
    @RequestMapping(value = "/{base64Email}/{base64Password}", method = RequestMethod.GET)
    public ResponseEntity<Account> findAccount(@PathVariable("base64Email") String base64Email,
                                               @PathVariable("base64Password") String base64Password) {
        String email = new String(Base64.decode(base64Email), Charset.forName("UTF-8"));
        String password = new String(Base64.decode(base64Password), Charset.forName("UTF-8"));
        Optional<Account> accountOptional = accountService.authenticate(email, password);
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
