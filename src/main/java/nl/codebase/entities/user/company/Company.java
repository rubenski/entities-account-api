package nl.codebase.entities.user.company;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Company {

    private UUID uuid;
    private String name;
    private String street;
    private String number;
    private String zip;
    private String commerceNumber;
    private String country;
    private String website;

}
