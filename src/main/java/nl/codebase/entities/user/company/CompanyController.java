package nl.codebase.entities.user.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CompanyController {

    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
    public CompanyForm get(@PathVariable("id") String id) {
        return new CompanyForm();
    }

    @RequestMapping(value = "/company", method = RequestMethod.PUT)
    public CompanyForm saveOrUpdate(@Valid @RequestBody CompanyForm company) {
        companyService.create(company);
        return company;
    }
}
