package nl.codebase.entities.user.signup;

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

    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
    public SignUpForm get(@PathVariable("id") String id) {
        return new SignUpForm();
    }

    @RequestMapping(value = "/company", method = RequestMethod.PUT)
    public SignUpForm saveOrUpdate(@Valid @RequestBody SignUpForm company) {
        signUpService.signUp(company);
        return company;
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
