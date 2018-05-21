package nl.codebase.entities.user.signup;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class Company {

    private Integer id;

    @NotNull
    @Pattern(regexp = "[\\d\\w\\s!?-_@%&+.]+")
    private String name;

    @NotNull
    @Pattern(regexp = "[\\d\\w\\s.-]{3,40}")
    private String street;

    @NotNull
    @Pattern(regexp = "[\\d\\w]{3,40}")
    private String number;

    @NotNull
    @Pattern(regexp = "[\\d]{4}[\\w]{2}")
    private String zip;

    @NotNull
    @Pattern(regexp = "[\\d\\w.#-]{4,40}")
    private String commerceNumber;

    @NotNull
    @Pattern(regexp = "[A-Z]{3}+")
    private String country;

    @Pattern(regexp = "^(http://|https://)?[\\d\\w._-]{2,40}(\\.[\\w]{2,3}){1,2}")
    private String website;

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
