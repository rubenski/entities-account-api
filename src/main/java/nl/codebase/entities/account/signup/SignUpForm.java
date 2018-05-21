package nl.codebase.entities.account.signup;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class SignUpForm {

    @Valid
    @NotNull
    private Account account;

    @Valid
    @NotNull
    private Company company;

}
