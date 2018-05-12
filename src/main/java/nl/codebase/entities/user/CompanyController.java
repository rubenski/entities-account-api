package nl.codebase.entities.user;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CompanyController {

    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
    public Company findCompany(@PathVariable("id") String id) {
        return new Company("Ruben corp", "Teststraat", "2");
    }

    @AllArgsConstructor
    private static class Company {
        private String name;
        private String street;
        private String number;
    }
}
