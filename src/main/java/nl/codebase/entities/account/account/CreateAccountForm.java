package nl.codebase.entities.account.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;

import static nl.codebase.entities.account.ApiConstants.PATTERN_PASSWORD;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateAccountForm extends UpdateAccountForm {


    @Pattern(regexp = PATTERN_PASSWORD)
    protected String password;

    @Pattern(regexp = PATTERN_PASSWORD)
    protected String confirmPassword;


    @AssertTrue
    @JsonIgnore
    public boolean isSamePassword() {
        return confirmPassword.equals(password);
    }

}
