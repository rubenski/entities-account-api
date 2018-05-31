package nl.codebase.entities.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountForm {

    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String PASSWORD_PATTERN = "[\\d\\w+=_)(*&!$%,.@#/}{\\^-]{8,20}";
    private static final String NAME_PATTERN = "[\\w\\s-']{2,20}";

    @NotNull
    @Pattern(regexp = NAME_PATTERN)
    protected String firstName;

    @NotNull
    @Pattern(regexp = NAME_PATTERN)
    protected String lastName;

    @NotNull
    @Email
    protected String email;

    @NotNull
    @Pattern(regexp = "[\\d\\s+]{10,20}")
    protected String phone;

    @Pattern(regexp = PASSWORD_PATTERN)
    protected String password;

    @Pattern(regexp = PASSWORD_PATTERN)
    protected String confirmPassword;


    @AssertTrue
    @JsonIgnore
    public boolean isSamePassword() {
        return confirmPassword.equals(password);
    }

}
