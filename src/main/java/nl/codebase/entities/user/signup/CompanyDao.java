package nl.codebase.entities.user.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
public class CompanyDao extends JdbcDaoSupport {

    private final DataSource dataSource;

    private static final String SQL_INSERT_COMPANY = "INSERT INTO f_company " +
            "(name, street, number, zip, commercenumber, country, website) VALUES (?,?,?,?,?,?,?)";

    @Autowired
    public CompanyDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public void insert(SignUpForm signUpForm) {
        getJdbcTemplate().update(SQL_INSERT_COMPANY, preparedStatement -> {
            preparedStatement.setString(1, signUpForm.getName());
            preparedStatement.setString(2, signUpForm.getStreet());
            preparedStatement.setString(3, signUpForm.getNumber());
            preparedStatement.setString(4, signUpForm.getZip());
            preparedStatement.setString(5, signUpForm.getCommerceNumber());
            preparedStatement.setString(6, signUpForm.getCountry());
            preparedStatement.setString(7, signUpForm.getWebsite());
        });
    }
}