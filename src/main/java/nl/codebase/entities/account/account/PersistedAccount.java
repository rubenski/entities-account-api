package nl.codebase.entities.account.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import nl.codebase.entities.common.account.Account;
import nl.codebase.entities.common.account.Grants;

import java.io.IOException;

@Getter
@Setter
public class PersistedAccount {

    private Account account = new Account();
    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private String password;
    private String salt;
    private Grants grants;

    public String getGrantsAsAsString() {
        try {
            return OBJECT_MAPPER.writer().writeValueAsString(account);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Could not serialize Grants to JSON", e);
        }
    }

    public void setGrantsFromString(String grantsString) {
        try {
            grants = OBJECT_MAPPER.readValue(grantsString, Grants.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not deserialize Grants to JSON", e);
        }
    }
}
