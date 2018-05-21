package nl.codebase.entities.account.signup;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import nl.codebase.entities.common.account.Grants;
import nl.codebase.entities.account.UserApiException;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class Account {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String PASSWORD_PATTERN = "[\\d\\w+=_)(*&!$%,.@#/}{\\^-]{8,20}";
    private static final String NAME_PATTERN = "[\\w\\s-']{2,20}";

    private Long id;

    @NotNull
    @Pattern(regexp = NAME_PATTERN)
    private String firstName;

    @NotNull
    @Pattern(regexp = NAME_PATTERN)
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Pattern(regexp = "[\\d\\s+]{10,20}")
    private String phone;

    @Pattern(regexp = PASSWORD_PATTERN)
    private String password;

    @Pattern(regexp = PASSWORD_PATTERN)
    private String confirmPassword;

    private boolean expired = false;
    private boolean enabled = false;
    private boolean locked = false;
    private Grants grants;

    @AssertTrue
    public boolean isSamePassword() {
        return confirmPassword.equals(password);
    }

    public String grantsAsString() {
        try {
            return OBJECT_MAPPER.writer().writeValueAsString(grants);
        } catch (JsonProcessingException e) {
            throw new UserApiException();
        }
    }

}
