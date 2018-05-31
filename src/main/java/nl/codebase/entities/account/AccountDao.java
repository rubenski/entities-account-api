package nl.codebase.entities.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import nl.codebase.entities.common.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Optional;

@Slf4j
@Repository
public class AccountDao extends JdbcDaoSupport {


    private final DataSource dataSource;

    private static final String SQL_FIND_ACCOUNT_BY_EMAIL = "SELECT * FROM f_account WHERE email = ?";
    private static final String SQL_INSERT_ACCOUNT = "INSERT INTO f_account (firstName, lastName, email, phone, " +
            "company_id, grants, password, salt) VALUES (?,?,?,?,?,?,?,?)";

    @Autowired
    public AccountDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public Optional<Account> findByEmail(String email) {
        return getJdbcTemplate().query(SQL_FIND_ACCOUNT_BY_EMAIL, ps -> ps.setString(1, email), rs -> {
            if(!rs.next()) {
                return Optional.empty();
            }

            Account account = new Account();
            account.setId(rs.getLong("id"));
            account.setEmail(rs.getString("email"));
            account.setEnabled(rs.getBoolean("enabled"));
            account.setExpired(rs.getBoolean("expired"));
            account.setFirstName(rs.getString("firstName"));
            account.setLastName(rs.getString("lastName"));
            account.setLocked(rs.getBoolean("locked"));
            account.setPhone(rs.getString("phone"));
            account.grantsFromString(rs.getString("grants"));
            account.setPassword(rs.getString("password"));
            account.setConfirmPassword(rs.getString("password"));
            account.setSalt(rs.getString("salt"));

            return Optional.of(account);
        });
    }

    public void insert(AccountForm account, int companyId, String salt, String password, String grants) {
        getJdbcTemplate().update(SQL_INSERT_ACCOUNT, preparedStatement -> {
            preparedStatement.setString(1, account.getFirstName());
            preparedStatement.setString(2, account.getLastName());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.setString(4, account.getPhone());
            preparedStatement.setInt(5, companyId);
            preparedStatement.setString(6, grants);
            preparedStatement.setString(7, password);
            preparedStatement.setString(8, salt);
        });
    }


}
