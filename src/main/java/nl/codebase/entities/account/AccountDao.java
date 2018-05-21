package nl.codebase.entities.account;

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
    private static final String SQL_INSERT_ACCOUNT = "INSERT INTO f_account (firstName, lastName, email, phone, expired, enabled, locked, company_id, grants) VALUES (?,?,?,?,?,?,?,?,?)";

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

    public void insert(Account account, int companyId) {
        getJdbcTemplate().update(SQL_INSERT_ACCOUNT, preparedStatement -> {
            preparedStatement.setString(1, account.getFirstName());
            preparedStatement.setString(2, account.getLastName());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.setString(4, account.getPhone());
            preparedStatement.setBoolean(5, account.isExpired());
            preparedStatement.setBoolean(6, account.isEnabled());
            preparedStatement.setBoolean(7, account.isLocked());
            preparedStatement.setInt(8, companyId);
            preparedStatement.setString(9, account.grantsAsString());
        });
    }
}
