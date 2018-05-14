package nl.codebase.entities.user.company;

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

    public void insert(CompanyForm companyForm) {
        getJdbcTemplate().update(SQL_INSERT_COMPANY, preparedStatement -> {
            preparedStatement.setString(1, companyForm.getName());
            preparedStatement.setString(2, companyForm.getStreet());
            preparedStatement.setString(3, companyForm.getNumber());
            preparedStatement.setString(4, companyForm.getZip());
            preparedStatement.setString(5, companyForm.getCommerceNumber());
            preparedStatement.setString(6, companyForm.getCountry());
            preparedStatement.setString(7, companyForm.getWebsite());
        });
    }

}
