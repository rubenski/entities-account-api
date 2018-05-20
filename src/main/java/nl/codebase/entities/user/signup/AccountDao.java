package nl.codebase.entities.user.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class AccountDao extends JdbcDaoSupport {

    private final DataSource dataSource;

    private static final String SQL_FIND_ACCOUNT_BY_EMAIL = "SELECT * FROM f_account WHERE email = ?";

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

            return Optional.of(account);
        });
    }

    public void insert(SignUpForm signUpForm) {


    }
}
