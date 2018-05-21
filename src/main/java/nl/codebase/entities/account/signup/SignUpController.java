package nl.codebase.entities.account.signup;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SignUpController {

    private SignUpService signUpService;

    @Autowired
    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('COMPANY_USER')")
    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
    public SignUpForm get(@PathVariable("id") String id) {
        return new SignUpForm();
    }

    @RequestMapping(value = "/signup", method = RequestMethod.PUT)
    public SignUpForm saveOrUpdate(@Valid @RequestBody SignUpForm signUpForm) {
        signUpService.signUp(signUpForm);
        return signUpForm;
    }

    @RequestMapping(value = "/exists", method = RequestMethod.POST)
    public boolean accountExists(@Valid @RequestBody AccountExists accountExists) {
        return signUpService.findAccountByEmail(accountExists.getEmail()).isPresent();
    }

    @Getter
    @Setter
    private static class AccountExists {
        @Email
        private String email;
    }
}
