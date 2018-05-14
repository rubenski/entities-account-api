package nl.codebase.entities.user.company;

import lombok.Setter;

import javax.validation.constraints.Pattern;

@Setter
public class CompanyForm {

    private Integer id;
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

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name.trim();
    }

    public String getStreet() {
        return street.trim();
    }

    public String getNumber() {
        return number.trim();
    }

    public String getZip() {
        return zip.trim();
    }

    public String getCommerceNumber() {
        return commerceNumber.trim();
    }

    public String getCountry() {
        return country.trim();
    }

    public String getWebsite() {
        return website != null ? website.trim() : null;
    }
}
