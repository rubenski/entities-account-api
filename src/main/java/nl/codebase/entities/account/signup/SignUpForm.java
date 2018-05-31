package nl.codebase.entities.account.signup;

import lombok.Getter;
import lombok.Setter;
import nl.codebase.entities.account.account.CreateAccountForm;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class SignUpForm {

    @Valid
    @NotNull
    private CreateAccountForm account;

    @Valid
    @NotNull
    private Company company;

}
