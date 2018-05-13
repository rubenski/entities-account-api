package nl.codebase.entities.user.company;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.UUID;

@Getter
@Setter
public class CompanyForm {

    private String uuid;
    @Pattern(regexp = "[\\d\\w\\s!?-_@%&+.]+")
    private String name;
    @Pattern(regexp = "[\\d\\w\\s!?-_@%&+.]{2,40}")
    private String street;
    @Pattern(regexp = "[\\d\\w\\s]{3,40}")
    private String number;
    @Pattern(regexp = "[\\d\\w\\s]{4,10}")
    private String zip;
    @Pattern(regexp = "[\\d\\w\\s.#-]{4,40}")
    private String commerceNumber;
    @Pattern(regexp = "[A-Z]{3}+")
    private String country;
    @Pattern(regexp = "[\\d\\w\\s!?-_@%&+./]{4,40}")
    private String website;

}
