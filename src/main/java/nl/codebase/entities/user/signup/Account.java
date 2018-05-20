package nl.codebase.entities.user.signup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean expired;
    private boolean enabled;
    private boolean locked;
}
