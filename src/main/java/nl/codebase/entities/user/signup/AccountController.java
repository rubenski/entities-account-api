package nl.codebase.entities.user.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.PathParam;
import java.util.Optional;

@Controller
public class AccountController {

    private AccountDao accountDao;

    @Autowired
    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @PreAuthorize(value = "hasAuthority('STANDARD_USER')")
    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public ResponseEntity<Account> findAccount(@PathParam("email") String email) {
        Optional<Account> accountOptional = accountDao.findByEmail(email);
        if(!accountOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accountOptional.get());
    }
}
