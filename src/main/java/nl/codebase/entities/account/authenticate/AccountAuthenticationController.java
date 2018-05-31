package nl.codebase.entities.account.authenticate;

import nl.codebase.entities.common.account.Account;
import org.postgresql.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.Optional;

@RestController
public class AccountAuthenticationController {

    private AccountAuthenticationService accountAuthenticationService;

    @Autowired
    public AccountAuthenticationController(AccountAuthenticationService accountAuthenticationService) {
        this.accountAuthenticationService = accountAuthenticationService;
    }

    // TODO: secure this with mutual TLS: https://stackoverflow.com/questions/33808603/implementing-2-way-ssl-using-spring-boot
    // Currently this is secured by not mapping the path in the Zuul proxy and assuming the app will deployed behind a NAT gateway
    @RequestMapping(value = "/{base64Email}/{base64Password}", method = RequestMethod.GET)
    public ResponseEntity<Account> findAccount(@PathVariable("base64Email") String base64Email,
                                               @PathVariable("base64Password") String base64Password) {
        String email = new String(Base64.decode(base64Email), Charset.forName("UTF-8"));
        String password = new String(Base64.decode(base64Password), Charset.forName("UTF-8"));
        Optional<Account> accountOptional = accountAuthenticationService.authenticate(email, password);
        if (!accountOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accountOptional.get());
    }
}




