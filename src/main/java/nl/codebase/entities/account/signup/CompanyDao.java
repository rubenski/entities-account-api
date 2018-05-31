package nl.codebase.entities.account.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
public class CompanyDao extends JdbcDaoSupport {

    private final DataSource dataSource;

    private static final String SQL_INSERT_COMPANY = "INSERT INTO f_company " +
            "(name, street, number, zip, commercenumber, country, website) VALUES (?,?,?,?,?,?,?)  RETURNING id";

    @Autowired
    public CompanyDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public int insert(Company company) {
        return getJdbcTemplate().query(SQL_INSERT_COMPANY, preparedStatement -> {
            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getStreet());
            preparedStatement.setString(3, company.getNumber());
            preparedStatement.setString(4, company.getZip());
            preparedStatement.setString(5, company.getCommerceNumber());
            preparedStatement.setString(6, company.getCountry());
            preparedStatement.setString(7, company.getWebsite());
        }, rs -> {
            rs.next();
            return rs.getInt(1);
        });
    }
}