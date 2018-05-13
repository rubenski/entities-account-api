package nl.codebase.entities.user.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.UUID;

@Repository
public class CompanyDao extends JdbcDaoSupport {

    private final DataSource dataSource;

    // Query returns the UUID using the Postgresql-specific and lovely 'RETURNING'
    private static final String SQL_INSERT_COMPANY = "INSERT INTO f_company " +
            "(uuid, name, street, number, zip, commercenumber, country, website) VALUES (?,?,?,?,?,?,?,?)";

    @Autowired
    public CompanyDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public String insert(CompanyForm companyForm) {
        UUID uuid = UUID.randomUUID();
        getJdbcTemplate().update(SQL_INSERT_COMPANY, preparedStatement -> {
            preparedStatement.setObject(1, uuid);
            preparedStatement.setString(2, companyForm.getName());
            preparedStatement.setString(3, companyForm.getStreet());
            preparedStatement.setString(4, companyForm.getNumber());
            preparedStatement.setString(5, companyForm.getZip());
            preparedStatement.setString(6, companyForm.getCommerceNumber());
            preparedStatement.setString(7, companyForm.getCountry());
            preparedStatement.setString(8, companyForm.getWebsite());
        });
        return uuid.toString();
    }

}
