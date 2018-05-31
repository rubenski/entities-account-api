package nl.codebase.entities.account.account;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static nl.codebase.entities.account.ApiConstants.PATTERN_NAME;
import static nl.codebase.entities.account.ApiConstants.PATTERN_PHONE;

@Getter
@Setter
public class UpdateAccountForm {

    @NotNull
    @Pattern(regexp = PATTERN_NAME)
    protected String firstName;

    @NotNull
    @Pattern(regexp = PATTERN_NAME)
    protected String lastName;

    @NotNull
    @Email
    protected String email;

    @NotNull
    @Pattern(regexp = PATTERN_PHONE)
    protected String phone;


}
